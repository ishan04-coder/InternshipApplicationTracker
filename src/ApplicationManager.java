import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class ApplicationManager {
    private ArrayList<InternshipApplication> applications;
    private ApplicationStore store;
    private int nextId;

    public ApplicationManager(ApplicationStore store) {
        this.store = store;
        applications = store.loadApplications();

        nextId = 1;

        for (InternshipApplication application : applications) {
            if (application.getId() >= nextId) {
                nextId = application.getId() + 1;
            }
        }
    }

    public InternshipApplication addApplication(String company, String role,
                                                String location,
                                                LocalDate deadline,
                                                Status status,
                                                String notes) {
        InternshipApplication application =
                new InternshipApplication(nextId, company, role, location,
                        deadline, status, notes);

        nextId++;
        applications.add(application);
        store.saveApplications(applications);

        return application;
    }

    public ArrayList<InternshipApplication> getApplications() {
        ArrayList<InternshipApplication> result =
                new ArrayList<>(applications);

        result.sort(Comparator.comparing(
                InternshipApplication::getDeadline));

        return result;
    }

    public ArrayList<InternshipApplication> search(String word) {
        ArrayList<InternshipApplication> result = new ArrayList<>();
        String searchWord = word.toLowerCase();

        for (InternshipApplication application : applications) {
            if (application.getCompany().toLowerCase().contains(searchWord)
                    || application.getRole().toLowerCase().contains(searchWord)
                    || application.getLocation().toLowerCase().contains(searchWord)
                    || application.getStatus().toString()
                    .toLowerCase().contains(searchWord)) {
                result.add(application);
            }
        }

        result.sort(Comparator.comparing(
                InternshipApplication::getDeadline));

        return result;
    }

    public InternshipApplication find(int id) {
        for (InternshipApplication application : applications) {
            if (application.getId() == id) {
                return application;
            }
        }

        return null;
    }

    public boolean changeStatus(int id, Status status) {
        InternshipApplication application = find(id);

        if (application == null) {
            return false;
        }

        application.setStatus(status);
        store.saveApplications(applications);

        return true;
    }

    public boolean remove(int id) {
        InternshipApplication application = find(id);

        if (application == null) {
            return false;
        }

        applications.remove(application);
        store.saveApplications(applications);

        return true;
    }

    public ArrayList<InternshipApplication> deadlinesWithin(int days) {
        ArrayList<InternshipApplication> result = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalDate lastDate = today.plusDays(days);

        for (InternshipApplication application : applications) {
            LocalDate deadline = application.getDeadline();

            if (!deadline.isBefore(today)
                    && !deadline.isAfter(lastDate)) {
                result.add(application);
            }
        }

        result.sort(Comparator.comparing(
                InternshipApplication::getDeadline));

        return result;
    }

    public int count(Status status) {
        int total = 0;

        for (InternshipApplication application : applications) {
            if (application.getStatus() == status) {
                total++;
            }
        }

        return total;
    }

    public int total() {
        return applications.size();
    }
}
