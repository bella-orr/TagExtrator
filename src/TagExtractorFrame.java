import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;

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
        chooseFileBtn.addActionListener((ActionEvent ae) -> getFile());

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));


        buttonPnl.add(chooseFileBtn);
        buttonPnl.add(quitBtn);

    }

    private void getFile()
    {
        JFileChooser fileChooser = new JFileChooser();

        int response = fileChooser.showOpenDialog(null); //selects file to open

        if(response == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getName();
            tagFrequencyArea.append("File name: " + filePath);
        }


    }


}
