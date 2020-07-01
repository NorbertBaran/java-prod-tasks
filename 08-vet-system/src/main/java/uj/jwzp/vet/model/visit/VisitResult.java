package uj.jwzp.vet.model.visit;

public class VisitResult {
    String description="";
    VisitStatus status=VisitStatus.MISSED;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }
}
