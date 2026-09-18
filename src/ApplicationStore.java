import java.io.*;
import java.util.ArrayList;

public class ApplicationStore {
    private final String fileName;

    public ApplicationStore(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<InternshipApplication> loadApplications() {
        ArrayList<InternshipApplication> list = new ArrayList<>();

        File file = new File(fileName);

        if (!file.exists()) {
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                try {
                    list.add(InternshipApplication.fromLine(line));
                } catch (Exception e) {
                    // Ignore a damaged line instead of stopping the program.
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read the saved data.");
        }

        return list;
    }

    public void saveApplications(ArrayList<InternshipApplication> list) {
        File file = new File(fileName);

        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println("# Internship Application Tracker");

            for (InternshipApplication application : list) {
                pw.println(application.saveLine());
            }
        } catch (IOException e) {
            System.out.println("Could not save the data.");
        }
    }
}
