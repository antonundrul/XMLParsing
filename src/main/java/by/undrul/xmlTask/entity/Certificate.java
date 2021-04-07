package by.undrul.xmlTask.entity;

import java.time.YearMonth;
import java.util.Objects;

public class Certificate {
    private String certificateNumber;
    private YearMonth dateOfIssue;
    private YearMonth dateOfExpiration;
    private String registeringOrganization;

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
        return Objects.hash(certificateNumber, dateOfIssue, dateOfExpiration, registeringOrganization);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "certificateNumber='" + certificateNumber + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfExpiration=" + dateOfExpiration +
                ", registeringOrganization='" + registeringOrganization + '\'' +
                '}';
    }
}
