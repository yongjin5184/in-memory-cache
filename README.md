## LRU in-memory cache Implementation

### 기능 정의

- Query
    - [x] 카테고리 리스트 조회
    - [x] 특정 카테고리에 속한 상품 리스트 조회
    - [x] 특정 상품에 대한 조회

- Data Loading and Reloading
    - [x] 캐시는 초기에 원본 DB 에서 로딩
    - [ ] Cache Miss 시, **적절한 시점**에 cache 는 **스스로** 해당 부분의 데이터를 원본 DB 에서 로딩

- Cache Data Eviction Policy
    - [x] Cache 내부의 데이터는 Eviction 처리
    - [x] 어떤 경우에 데이터 Eviction 할지 결정
    - [x] 어떤 데이터를 우선적으로 Eviction 시킬 지 정책 정의
    - [x] 코드에 주석으로 정책을 사용했는지 서술

- Cache Optimization
    - [ ] Cache Miss 를 최소화하기 위한 비즈니스 로직이나 알고리즘 제안

- 원본 데이터에 대한 CRUD
    - [ ] 특정 카테고리명을 변경
    - [ ] 특정 상품명을 변경
    - [ ] 특정 상품의 가격을 변경
        
