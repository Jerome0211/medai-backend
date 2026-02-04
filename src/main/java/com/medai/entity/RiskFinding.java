package com.medai.entity;

public class RiskFinding {

    private String riskCode;
    private String level;       // INFO / WARN / BLOCK（后面会换 enum）
    private String message;

    public RiskFinding() {}

    public RiskFinding(String riskCode, String level, String message) {
        this.riskCode = riskCode;
        this.level = level;
        this.message = message;
    }

    public String getRiskCode() { return riskCode; }
    public String getLevel() { return level; }
    public String getMessage() { return message; }

    public void setRiskCode(String riskCode) { this.riskCode = riskCode; }
    public void setLevel(String level) { this.level = level; }
    public void setMessage(String message) { this.message = message; }
}
