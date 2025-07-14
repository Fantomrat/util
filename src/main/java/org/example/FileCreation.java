package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class FileCreation {
    String path;
    Boolean addingMode;


    public FileCreation(String resultPath, boolean addingMode) {
        String systemPath;
        try {
            systemPath = (new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())).getParent();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        path = (systemPath + "/" + resultPath).replace("\\", "/");
        this.addingMode = addingMode;

        try {
            File dir = new File(path);

            if (!dir.exists()) {
                dir.mkdirs();
            }

        } catch (Exception e) {
            System.out.println("Error when creating a path:");
            throw new RuntimeException(e);
        }


    }

    public int createFile(String prefix, String name, ArrayList<String> content) {

        if (!content.isEmpty()) {
            try {
                File file = new File(path + "/" + prefix + name + ".txt");

                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fileWriter = new FileWriter(file, addingMode);

                for (String text : content) {
                    fileWriter.write(text + "\n");
                }
                fileWriter.close();
                return content.size();

            } catch (IOException e) {
                 System.out.println("Error when creating file '" + prefix + name + ".txt'");
                 e.printStackTrace();
                 return 0;
            }
        }

        return 0;

    }
}
