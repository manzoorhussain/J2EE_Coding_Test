package com.smallworld.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/*@Getter
@Setter*/
@Data
public class Transaction {
    @JsonProperty("mtn")
    private Long Mtn;
    @JsonProperty("amount")
    private Double Amount;
    @JsonProperty("senderFullName")
    private String SenderFullName;
    @JsonProperty("senderAge")
    private Integer SenderAge;
    @JsonProperty("beneficiaryFullName")
    private String BeneficiaryFullName;
    @JsonProperty("beneficiaryAge")
    private Integer BeneficiaryAge;
    @JsonProperty("issueId")
    private Integer IssueId;
    @JsonProperty("issueSolved")
    private Boolean IssueSolved;
    @JsonProperty("issueMessage")
    private String IssueMessage;

    @Override
    public boolean equals(Object obj) {
        Transaction trx = (Transaction) obj;
        return this.getMtn().equals(trx.getMtn());
    }
    @Override
    public int hashCode() {
        return Mtn.hashCode();
    }

    public Long getMtn() {
        return Mtn;
    }

    public void setMtn(Long mtn) {
        Mtn = mtn;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getSenderFullName() {
        return SenderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        SenderFullName = senderFullName;
    }

    public Integer getSenderAge() {
        return SenderAge;
    }

    public void setSenderAge(Integer senderAge) {
        SenderAge = senderAge;
    }

    public String getBeneficiaryFullName() {
        return BeneficiaryFullName;
    }

    public void setBeneficiaryFullName(String beneficiaryFullName) {
        BeneficiaryFullName = beneficiaryFullName;
    }

    public Integer getBeneficiaryAge() {
        return BeneficiaryAge;
    }

    public void setBeneficiaryAge(Integer beneficiaryAge) {
        BeneficiaryAge = beneficiaryAge;
    }

    public Integer getIssueId() {
        return IssueId;
    }

    public void setIssueId(Integer issueId) {
        IssueId = issueId;
    }

    public Boolean getIssueSolved() {
        return IssueSolved;
    }

    public void setIssueSolved(Boolean issueSolved) {
        IssueSolved = issueSolved;
    }

    public String getIssueMessage() {
        return IssueMessage;
    }

    public void setIssueMessage(String issueMessage) {
        IssueMessage = issueMessage;
    }

    @Override
    public String toString() {
        return "\n{\n" +
                "mtn: " + getMtn() + "\n" +
                "amount: " + getAmount() + "\n" +
                "senderFullName: " + getSenderFullName() + "\n" +
                "senderAge: " + getSenderAge() + "\n" +
                "beneficiaryFullName: " + getBeneficiaryFullName() + "\n" +
                "beneficiaryAge: " + getBeneficiaryAge() + "\n" +
                "issueId: " + getIssueId() + "\n" +
                "issueSolved: " + getIssueSolved() + "\n" +
                "issueMessage: " + getIssueMessage() + "\n" +
                "}";
    }
}
