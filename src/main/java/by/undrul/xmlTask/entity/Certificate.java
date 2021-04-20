package by.undrul.xmlTask.entity;

import java.time.YearMonth;

public class Certificate {
    private String certificateNumber;
    private YearMonth dateOfIssue;
    private YearMonth dateOfExpiration;
    private String registeringOrganization;

    public Certificate(String certificateNumber, YearMonth dateOfIssue, YearMonth dateOfExpiration, String registeringOrganization) {
        this.certificateNumber = certificateNumber;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiration = dateOfExpiration;
        this.registeringOrganization = registeringOrganization;
    }

    public Certificate() {

    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public YearMonth getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(YearMonth dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public YearMonth getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(YearMonth dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public String getRegisteringOrganization() {
        return registeringOrganization;
    }

    public void setRegisteringOrganization(String registeringOrganization) {
        this.registeringOrganization = registeringOrganization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return certificateNumber.equals(that.certificateNumber) &&
                dateOfIssue.equals(that.dateOfIssue) &&
                dateOfExpiration.equals(that.dateOfExpiration) &&
                registeringOrganization.equals(that.registeringOrganization);
    }

    @Override
    public int hashCode() {

        int prime = 31;
        int result = 1;

        result = result * prime + certificateNumber.hashCode();
        result = result * prime + dateOfIssue.hashCode();
        result = result * prime + dateOfExpiration.hashCode();
        result = result * prime + registeringOrganization.hashCode();

        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" Certificate{\n");
        sb.append("  certificate number : ").append(certificateNumber).append('\n');
        sb.append("  date of issue : ").append(dateOfIssue).append('\n');
        sb.append("  date of expiration : ").append(dateOfExpiration).append("\n");
        sb.append("  registering organization : ").append(registeringOrganization).append('\n');
        sb.append(" }");
        return sb.toString();
    }
}
