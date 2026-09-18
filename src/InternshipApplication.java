import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InternshipApplication {
    private int id;
    private String company;
    private String role;
    private String location;
    private LocalDate deadline;
    private Status status;
    private String notes;

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public InternshipApplication(int id, String company, String role,
                                 String location, LocalDate deadline,
                                 Status status, String notes) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.location = location;
        this.deadline = deadline;
        this.status = status;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getRole() {
        return role;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String saveLine() {
        return id + "|" + clean(company) + "|" + clean(role) + "|"
                + clean(location) + "|" + deadline + "|" + status + "|"
                + clean(notes);
    }

    public static InternshipApplication fromLine(String line) {
        String[] p = line.split("\\|", -1);

        if (p.length != 7) {
            throw new IllegalArgumentException("Bad application record");
        }

        return new InternshipApplication(
                Integer.parseInt(p[0]),
                p[1],
                p[2],
                p[3],
                LocalDate.parse(p[4]),
                Status.valueOf(p[5]),
                p[6]
        );
    }

    private String clean(String value) {
        return value.replace("|", "/").replace("\n", " ");
    }

    public String displayDate() {
        return deadline.format(FORMAT);
    }

    @Override
    public String toString() {
        return String.format("%-4d %-18s %-23s %-15s %-12s %-12s",
                id, company, role, location, displayDate(), status);
    }
}
