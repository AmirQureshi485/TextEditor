import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menuBar;
    JTextArea textArea;
    JMenu File, Edit;

    JMenuItem newFile, openFile, saveFile;

    JMenuItem cut, copy, paste, selectAll, close;

    TextEditor(){
        // initialize frame
        frame= new JFrame();
        // initialize menuBar
        menuBar= new JMenuBar();
        //Initialize textArea
        textArea= new JTextArea();
        // initialize menu;
        File= new JMenu("File");
        Edit= new JMenu("Edit");
        // initialize File menuItem
        newFile= new JMenuItem("New Window");
        openFile= new JMenuItem("Open File");
        saveFile= new JMenuItem("Save File");
        //add ActionListener to file menu item
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //add menuItem in File
        File.add(newFile);
        File.add(openFile);
        File.add(saveFile);
        //Initialize Edit menuItem
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        //add ActionListener to edit menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        // add menuItem in Edit
        Edit.add(cut);
        Edit.add(copy);
        Edit.add(paste);
        Edit.add(selectAll);
        Edit.add(close);

        // add menu in menBar
        menuBar.add(File);
        menuBar.add(Edit);

        frame.setJMenuBar(menuBar);
        //create content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        // create scroll pane
        JScrollPane scrollPane= new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scroll pane to panel
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);

        frame.setBounds(100,100, 400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            // perform cut operation
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            System.exit(0);
        }
        if (actionEvent.getSource() == openFile) {
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we have clicked open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // getting selected files
                File file = fileChooser.getSelectedFile();
                // getting yhe path of selected file
                String filePath = file.getPath();
                try {
                    //initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    // initialize buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // read content of line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    // set the output string to textArea
                    textArea.setText(output);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource() == saveFile) {
            // initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // if we click on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                // create a new file with choosen directed path and file name
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                   // initialize file writer
                    FileWriter fileWriter=new FileWriter(file);
                    // initialize buffered writer
                    BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                    // write content of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                     ioException.printStackTrace();
                }
            }

        }
        if(actionEvent.getSource()==newFile){
            // open new text editor
            TextEditor newTextEditor=new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor= new TextEditor();
    }
}