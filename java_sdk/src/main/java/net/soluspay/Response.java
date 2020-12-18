package net.soluspay;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Response implements Serializable, ReponseData{



    private String responseMessage;
    private String responseStatus;
    private Integer responseCode;
    private String tranDateTime;
    private String terminalId;
    private Integer systemTraceAuditNumber;
    private String clientId;
    private String PAN;
    private Float tranAmount;
    private Float tranFee;
    private Float additionalAmount;
    private Float acqTranFee;
    private Float issuerTranFee;
    private String pubKeyValue;
    private String tranCurrency;
    private String paymentInfo;
    private String fromAccount;
    private HashMap<String, Double> balance;
    private HashMap<String, String> billInfo;


    public HashMap<String, String> getBillInfo() {
        return billInfo;
    }

    public HashMap<String, Double> getBalance() {
        return balance;
    }

    public String getAvailableBalance() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(this.getBalance().get("available"));

    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public String getToAccount() {
        return toAccount;
    }

    public String getTranCurrency() {
        return tranCurrency;
    }

    public Float getAcqTranFee() {
        return acqTranFee;
    }

    public Float getIssuerTranFee() {
        return issuerTranFee;
    }

    public String getPubKeyValue() {
        return pubKeyValue;
    }

    public Float getAdditionalAmount() {
        return additionalAmount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public Float getTranFee() {
        return tranFee;
    }

    public String getToCard() {
        return toCard;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getTranDateTime() {
        Date newDate = new Date();
        // we have to set the locale as this will fail in a non en-US ones!
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try {
            newDate = dateFormat.parse(tranDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat.format(newDate);
    }

    public String getClientId() {
        return clientId;
    }

    public String getPAN() {
        return PAN;
    }

    public Float getTranAmount() {
        return tranAmount;
    }


    public String getEbsError() {
        return errorMessage.getEbsMessage();
    }

    public Integer getEbsCode() {
        return errorMessage.getEbsCode();
    }

    /**
     * @param code error code, get it from onError error.getErrorCode()
     * @return String text of the error that occurred.
     * <p>
     * This method is particularly interesting in that it does magic things:
     * - it checks if the error code is *NOT* zero (to handle ebs case)
     * - the other case will be for non-ebs errors.
     */
    public String getError(Integer code) {
        if (code >= 400 && code < 500) {
            // a validation error - and other form of errors
            return this.getCode();
        } else {
            return errorMessage.getMessage();
        }
    }

    /*
     * A helper functions that works for both classes of errors; whether they are ebs or not
     */
    public Integer getEbsCode(Integer code) {
        if (errorMessage.isEbsError()) {
            return errorMessage.getEbsCode();
        } else {
            return errorMessage.getCode();
        }
    }


    @Override
    public boolean isSuccessful() {
        return this.responseCode != 0;

    }
}
