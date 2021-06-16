package javatypeapp.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsProcessKiller {

    // command used to get list of running task
    private static final String TASKLIST = "tasklist";
    // command used to kill a task
    private static final String KILL = "taskkill /F /IM ";

    public boolean isProcessRunning(String serviceName) {

        try {
            Process pro = Runtime.getRuntime().exec(TASKLIST);
            BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(serviceName)) {
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void killProcess(String serviceName) {

        try {
            Runtime.getRuntime().exec(KILL + serviceName);
            System.out.println(serviceName + " killed successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}