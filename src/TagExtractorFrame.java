import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class TagExtractorFrame extends JFrame
{
    //JPanels
    JPanel mainPnl;
    JPanel textPnl;
    JPanel buttonPnl;

    //JTextArea
    JTextArea tagFrequencyArea;

    //JScrollPane
    JScrollPane scroller;

    //JButtons
    JButton chooseFileBtn;
    JButton quitBtn;

    //Map
    Map<String, Integer> wordFrequency = new HashMap<>();

    Set<String> stopWords = new TreeSet<>();

    JFileChooser chooser = new JFileChooser();
    File selectedFile;
    String line = "";





    public TagExtractorFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createButtonPnl();
        mainPnl.add(buttonPnl, BorderLayout.NORTH);


        createTextPnl();
        mainPnl.add(textPnl, BorderLayout.CENTER);


        add(mainPnl);
        setTitle("Tag Extractor");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void createTextPnl()
    {
        textPnl = new JPanel();

        tagFrequencyArea = new JTextArea(50,50);
        tagFrequencyArea.setEditable(false);

        scroller = new JScrollPane(tagFrequencyArea);
        textPnl.add(scroller);


    }

    private void createButtonPnl()
    {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1,2));

        chooseFileBtn = new JButton("Choose File");
        chooseFileBtn.addActionListener((ActionEvent ae) -> getStop());

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));


        buttonPnl.add(chooseFileBtn);
        buttonPnl.add(quitBtn);

    }

    private void getFile()
    {
        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();

                Path file = selectedFile.toPath();

                tagFrequencyArea.append("File name: " + selectedFile.getName());

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                line = reader.readLine();

                while (line!= null)
                {

                    if(!line.trim().equals(""))
                    {
                        String [] words = line.split(" ");

                        for (String w : words)
                        {
                            if(w == null || w.trim().equals(""))
                            {
                                continue;
                            }

                            String processedWord = w.toLowerCase();
                            processedWord = processedWord.replace(",","");
                            processedWord = processedWord.replace(".","");
                            processedWord = processedWord.replace("!","");
                            processedWord = processedWord.replace("?","");


                            if(wordFrequency.containsKey(processedWord))
                            {
                                wordFrequency.put(processedWord, wordFrequency.get(processedWord)+1);
                            }

                            else
                            {
                                wordFrequency.put(processedWord,1);
                            }
                        }
                    }


                    line = reader.readLine();

                }

                System.out.println(wordFrequency);


            }
            else
            {
                tagFrequencyArea.append("You must choose a file. Program terminating.");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            tagFrequencyArea.append("File not found.");
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void getStop()
    {
        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();

                Path file = selectedFile.toPath();

                tagFrequencyArea.append("File name: " + selectedFile.getName());

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                line = reader.readLine();

                while (line!= null)
                {
                    stopWords.add(line);
                    line = reader.readLine();
                }

                System.out.println(stopWords);


            }
            else
            {
                tagFrequencyArea.append("You must choose a file. Program terminating.");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            tagFrequencyArea.append("File not found.");
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }


}

