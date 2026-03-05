# Loan Application Evaluation Service

### Overall approach
- Structured the application in separation of concern based business logic so that each rule has dedicated class.
- LoanApplication service acts as orchestrator for evaluation of application

### Key design decisions 
- 1. **use of BigDecimal**: use of BigDecimal for all monetary calculation and rounding up scaled to 2 to minimize rounding error
- 2. **Layered Architecture**: made used of layered architecture (Controller -> Service -> RuleEngine -> Repository)
- 3. **in-memory H2 DB**: Made use of in-memory db to reduce external dependency, but may be changed to POSTGRESQL is required with no code changes
- 4. ** Consolidate all Rejection Reason**: evaluation of every rule and no short-circuit return for better understanding of rejection

### Trade-offs considered

- 1. ** Consolidate all Rejection Reason**: evaluation of every rule and no short-circuit will result in every rule been evaluated
- 2. **In-Memory DB**: Data will get flushed on restart of service since it used in-memory volatile nature.

### Assumptions made 

- 1. **Offer validity and Eligibility**
 - Eligibility > 60% income -> Rejected
 - offer valid > 50% income -> Rejected
 - Offer validate check is applied after Eligibility check
 
- 2. **Null risk band for rejection**
 - Since the offer is rejected the risk band will be null
 
- 3. **Loan > 10,00,000**: Loan amt of exactly 10 lac will not attract premium
 	
 	
### Proposed Improvements  

1. **Authentication**: Add RBAC Authentication to the controllers

2. **Logging**: Add logging for easy debugging

3. **Documentation**: Enhance swagger and code documentation for easy integration and understanding

4. **Audit Trail**: Add auditing at every stage of evaluation

5. **Configurable and Cached Values**: Values used in the application which may change can be configured at DB Level and can be cached to reduce performance overhead
