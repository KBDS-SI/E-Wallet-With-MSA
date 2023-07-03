# E-Wallet-With-MSA

`KB DataSystem` `Cloud` `MSA`

## 프로젝트 소개

2023.7.17에 투입될 『KB스타뱅킹 회원정책 개편』 프로젝트에 앞서 MSA 구조를 학습하기 위한 Side 프로젝트 입니다. 투입될 프로젝트의 간단한 기능구현을 통해 새로운 개발환경에 대한 적응을 목표로합니다.

<br>

## 구현 기능

- 회원가입
  - ID/PW 방식으로 회원을 가입한다.
  - ID는 영문만 가능 (5~20자리)
  - ID중복체크 기능을 제공한다.
- 로그인
  - ID/PW로 로그인을 제공한다.
  - 중복로그인을 방지한다.
- 홈화면 제공
  - 로그아웃버튼을 제공한다.
  - 최근접속시간, 지갑잔액, 최근이력(충전/송금) 5건을 제공한다.
- 전자지갑
  - 생성
    - 전자지갑은 1인당 1개만 생성
  - 조회
    - 지갑의 잔액을 조회한다.
    - 지갑의 이력을 조회한다.
  - 송금
    - 상대방 id지정, 금액 입력 후 송금
    - 송금취소 기능을 제공한다. (30분 이내에 취소가능)
  - 충전
    - 충전기능을 제공한다.
    - 충전은 Mystar 입금처럼 진행.
    - 로그인 후 충전금액 입력 후 입력하면 충전완료

<br>

## 🚀 기술 스택

### Programming Languages

<img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white"/>

### Framework

<img alt="Spring" src="https://img.shields.io/badge/spring%20-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white"/>
<img alt="Maven" src="https://img.shields.io/badge/maven%20-%2335495e.svg?&style=for-the-badge&logo=maven&logoColor=%234FC08D"/>
<img alt="JPA" src="https://img.shields.io/badge/jpa%20-%23009639.svg?&style=for-the-badge&logo=jpa&logoColor=white"/>

### Databases