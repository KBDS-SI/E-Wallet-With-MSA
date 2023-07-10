package com.kbds.PayMentService.service;

import com.example.ewallet.vo.RequestEwallet;
import com.example.ewallet.vo.ResponseEwallet;
import com.kbds.PayMentService.client.EwalletServiceClient;
//import com.kbds.PayMentService.client.RemitServiceClient;
import com.kbds.PayMentService.dto.PayMentDto;
import com.kbds.PayMentService.jpa.PayMentEntity;
import com.kbds.PayMentService.jpa.PayMentRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Data
@Slf4j
@Service
public class PayMentServiceImp implements PayMentService{
    PayMentRepository payMentRepository;

    EwalletServiceClient ewalletServiceClient;

//    RemitServiceClient remitServiceClient;

    @Autowired
    public PayMentServiceImp(PayMentRepository payMentRepository, EwalletServiceClient ewalletServiceClient/*, RemitServiceClient remitServiceClient*/) {
        this.payMentRepository = payMentRepository;
        this.ewalletServiceClient = ewalletServiceClient;
//        this.remitServiceClient = remitServiceClient;
    }

    @Override
    public PayMentDto createPayMent(PayMentDto paymentDto) {
        ResponseEntity<ResponseEwallet> responseEwallet = ewalletServiceClient.getSearchEwallet(paymentDto.getSendId());
        paymentDto.setEwalletId(responseEwallet.getBody().getEwalletId());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PayMentEntity payMentEntity = mapper.map(paymentDto, PayMentEntity.class);
        log.info("payMentEntity : " + payMentEntity.toString());
        payMentRepository.save(payMentEntity);

        PayMentDto returnPayMentDto = mapper.map(payMentEntity, PayMentDto.class);
        return returnPayMentDto;
    }

    @Override
    public Iterable<PayMentEntity> getReceivePayList(String receiveId) {
        return payMentRepository.findByReceiveId(receiveId);
    }

    @Override
    public String receivePayMent(PayMentDto payMentDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PayMentEntity payMentEntity = mapper.map(payMentDto, PayMentEntity.class);

        payMentEntity = payMentRepository.findByUseId(payMentEntity.getUseId());

        if (payMentEntity != null) {
            // 전자지갑 잔액 UPDATE
            RequestEwallet requestEwallet = new RequestEwallet();
            /*private String userId;
              private String ewalletId;
              private BigDecimal amt
              private String addYn;*/
            requestEwallet.setUserId(payMentEntity.getReceiveId()); // 받는사람이 본인이라서 받는사람 ID 세팅
            requestEwallet.setEwalletId(""); // 전자지갑ID는 전자지갑 모듈에서 세팅해서 UPDATE 처리...
            requestEwallet.setAmt(new BigDecimal(payMentEntity.getSendAmt().toString()));
            requestEwallet.setAddYn("1"); // 입금처리
            ResponseEntity<ResponseEwallet> reponseEwallet = ewalletServiceClient.updateBalance(requestEwallet);

//            // 입출금이력 정보 UPDATE
//            /*
//            private String userId;
//            private String ewalletId;
//            private String remitCode;
//            private BigDecimal amt;
//            private String memo;
//            private String oppoUserId;
//            private String cancelYn;
//            private BigDecimal finBal;
//             */
//            RequestRemit requestRemit = new RequestRemit();
//            requestRemit.setUserId(payMentEntity.getReceiveId());
//            requestRemit.setEwalletId(reponseEwallet.getBody().getEwalletId());
//            requestRemit.setRemitCode("1");
//            requestRemit.setAmt(new BigDecimal(payMentEntity.getSendAmt().toString()));
//            requestRemit.setOppoUserId(payMentEntity.getSendId());
//            requestRemit.setMemo("송금 받음");
//            requestRemit.setCancelYn("0");
//            requestRemit.setFinBal(reponseEwallet.getBody().getAmt());
//            ResponseEntity responseEntity = remitServiceClient.createRemitFromPayMent(requestRemit);

            // 임시 송금테이블 삭제
            payMentRepository.deleteById(payMentEntity.getUseId());
        }

        return "송금이 완료 되었습니다.";
    }
}
