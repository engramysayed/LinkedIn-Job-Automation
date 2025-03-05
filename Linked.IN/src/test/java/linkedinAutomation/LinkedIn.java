package linkedinAutomation;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import com.microsoft.edge.seleniumtools.EdgeOptions;

import javax.swing.ListModel;

public class LinkedIn {
    
	private static  String [] searchKeyWords=new String[100];
	private static  String [] blackListKeyWords=new String[100];
	private static int keyWordsCounter=0,blackListCounter=0;
	private JSpinner jobSpinner;
	private JPanel personalInfo,jobInfo,resultPanel;
	private  JTable resultTable,searchTable,blTable;
	private static DefaultTableModel resultModel,searchmodel,blmodel;
	private  JButton jobInfoBtn,homeBtn,personalInfoBtn,runAutomationButton;
	private JFrame frame;
	private static EdgeDriver driver;
    private JTextField firstname,lastname,city,phone,yearsOfExp,linkedIn,Headline,Summary,Cover,noticePeriod,currentSalary;
    JLabel stats;
    private JComboBox<String> pronounsComboBox,englishProficiencyComboBox,jobTypeComboBox;
    private Map<String, String> cvSections;
    private static final List<String> ALLOWED_SECTIONS = Arrays.asList(
            "Profile", "Education", "Professional Experience", "Courses", "Skills", "Languages");
    private Map<String, String> languagesMap;
    private static List<String> askedQuestions = new ArrayList<>();

   
    private final JPanel settings = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(
                0, 0, Color.decode("#aa4b6b"),
                getWidth(), getHeight(), Color.decode("#6b6b83")
            );
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    private final JPanel createdBy = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(
                0, 0, Color.decode("#aa4b6b"),
                getWidth(), getHeight(), Color.decode("#6b6b83")
            );
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        
        }
    };
 
    
    
    /**
     * @wbp.parser.entryPoint
     */
    public LinkedIn() {
    	

        GradientTextPanel hometxt = new GradientTextPanel("Developed By: Ramy Sayed", new Font("Times New Roman", Font.BOLD, 25));
        hometxt.setBounds(150, 39, 300, 63);
        
    	try {
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
    			| UnsupportedLookAndFeelException e1) {
    		e1.printStackTrace();
    		}
    	
        frame = new JFrame("LinkedIn Automator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(693, 226);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        
         
        
        

		 
        createdBy.setBounds(131, 0, 546, 198);
        frame.getContentPane().add(createdBy);
        createdBy.setLayout(null);
        createdBy.add(hometxt);
        
         
         
        personalInfo = new JPanel();
        personalInfo.setBounds(131, 0, 552, 186);
        frame.getContentPane().add(personalInfo);
        personalInfo.setLayout(null);
        personalInfo.setVisible(false);
        
                JLabel label = new JLabel("First Name:");
                label.setBounds(30, 11, 111, 23);
                personalInfo.add(label);
                
                
                firstname = new JTextField();
                firstname.setBounds(151, 11, 111, 23);
                personalInfo.add(firstname);
                
                        JLabel label_1 = new JLabel("Last Name:");
                        label_1.setBounds(305, 11, 111, 23);
                        personalInfo.add(label_1);
                        
                        
                                                lastname = new JTextField();
                                                lastname.setBounds(426, 11, 111, 23);
                                                personalInfo.add(lastname);
                                                
                                                        JLabel label_2 = new JLabel("City:");
                                                        label_2.setBounds(30, 45, 111, 23);
                                                        personalInfo.add(label_2);
                                                        
                                                        city = new JTextField();
                                                        city.setBounds(151, 45, 111, 23);
                                                        personalInfo.add(city);
                                                        
                                                                JLabel label_3 = new JLabel("Mobile:");
                                                                label_3.setBounds(305, 45, 111, 23);
                                                                personalInfo.add(label_3);
                                                                
                                                                
                                                                                                        phone = new JTextField();
                                                                                                        phone.setBounds(426, 45, 111, 23);
                                                                                                        personalInfo.add(phone);
                                                                                                        
                                                                                                                JLabel label_4 = new JLabel("Photo:");
                                                                                                                label_4.setBounds(30, 79, 111, 23);
                                                                                                                personalInfo.add(label_4);
                                                                                                                
                                                                                                                
                                                                                                                JButton uploadPhotoButton = new JButton("Upload Photo");
                                                                                                                uploadPhotoButton.setEnabled(false);
                                                                                                                uploadPhotoButton.addActionListener(new ActionListener() {
                                                                                                                	public void actionPerformed(ActionEvent e) {
                                             			JFileChooser filechooser = new JFileChooser();
                                             			filechooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));

                                             			int response = filechooser.showOpenDialog(null);
                                             			if(response == JFileChooser.APPROVE_OPTION) {
                                             				File photoPaths = new File(filechooser.getSelectedFile().getAbsolutePath());                                             		 
                                             				photoPath=""+photoPaths;
                                             			}
                                             			System.out.print(photoPath);
                                                                                                                	}
                                                                                                                });
                                                                                                                uploadPhotoButton.setBounds(151, 79, 111, 23);
                                                                                                                
                                                                                                                
                                                                                                                personalInfo.add(uploadPhotoButton);
                                                                                                                
                                                                                                                        JLabel label_5 = new JLabel("CV:");
                                                                                                                        label_5.setBounds(305, 79, 111, 23);
                                                                                                                        personalInfo.add(label_5);
                                                                                                                        
                                                                                                                        JButton uploadCVButton = new JButton("Upload CV");
                                                                                                                        uploadCVButton.setEnabled(false);
                                                                                                                        uploadCVButton.addActionListener(new ActionListener() {
                                                                                                                        	public void actionPerformed(ActionEvent e) {
                                                     			JFileChooser filechooser = new JFileChooser();
                                                     			filechooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));

                                                     			int response = filechooser.showOpenDialog(null);
                                                     			if(response == JFileChooser.APPROVE_OPTION) {
                                                     				File resumePaths= new File(filechooser.getSelectedFile().getAbsolutePath());
                                                     				resumePath=""+resumePaths;
                                                     				 noCv=false;
                                                     			}
                                                                                                                        	}
                                                                                                                        });
                                                                                                                        uploadCVButton.setBounds(426, 79, 111, 23);
                                                                                                                        
                                                                                                                        personalInfo.add(uploadCVButton);
                                                                                                                        
                                                                                                                                JLabel label_6 = new JLabel("Pronouns:");
                                                                                                                                label_6.setBounds(30, 113, 111, 23);
                                                                                                                                personalInfo.add(label_6);
                                                                                                                                
                                                                                                                                pronounsComboBox = new JComboBox<>(new String[]{"Male", "Female"});
                                                                                                                                pronounsComboBox.setBounds(151, 113, 111, 23);
                                                                                                                                personalInfo.add(pronounsComboBox);
                                                                                                                                
                                                                                                                                        JLabel label_8 = new JLabel("Proficiency in English:");
                                                                                                                                        label_8.setBounds(30, 147, 111, 23);
                                                                                                                                        personalInfo.add(label_8);
                                                                                                                                        
                                                                                                                                        
                                                                                                                                        englishProficiencyComboBox = new JComboBox<>(new String[]{"Professional", "Native or bilingual", "Conversational", "None"});
                                                                                                                                        englishProficiencyComboBox.setBounds(151, 147, 111, 23);
                                                                                                                                        personalInfo.add(englishProficiencyComboBox);
                                                                                                                                        
                                                                                                                                        yearsOfExp = new JTextField();
                                                                                                                                        yearsOfExp.setBounds(426, 147, 111, 23);
                                                                                                                                        personalInfo.add(yearsOfExp);
                                                                                                                                        
                                                                                                                                        JLabel lblYearsOfExperience = new JLabel("Years of experience:");
                                                                                                                                        lblYearsOfExperience.setBounds(305, 147, 111, 23);
                                                                                                                                        personalInfo.add(lblYearsOfExperience);
                                                                                                                                        
                                                                                                                                        JLabel label_2_1_1 = new JLabel("LinkedIn profile link:");
                                                                                                                                        label_2_1_1.setBounds(305, 113, 111, 23);
                                                                                                                                        personalInfo.add(label_2_1_1);
                                                                                                                                        
                                                                                                                                        linkedIn = new JTextField();
                                                                                                                                        linkedIn.setBounds(426, 113, 111, 23);
                                                                                                                                        personalInfo.add(linkedIn);
        
        
        
 
         
        jobInfo = new JPanel();
        jobInfo.setBounds(131, 0, 552, 186);
        frame.getContentPane().add(jobInfo);
        jobInfo.setLayout(null);
        jobInfo.setVisible(false);
        
        
        runAutomationButton = new JButton("Run Automation");
        runAutomationButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		runAutomationButton.setEnabled(false);
        	    new Thread(() -> startAutomation()).start(); // Run automation in a separate thread
        	   
        		
        	}
        });
        
        JScrollPane searchkeywords = new JScrollPane();
        searchkeywords.setOpaque(false);
        searchkeywords.setBounds(389, 27, 142, 48);
        jobInfo.add(searchkeywords);
        
        
        
        runAutomationButton.setBounds(253, 135, 115, 23);
        jobInfo.add(runAutomationButton);
        
 
                JTextField keywordField = new JTextField(20);
                keywordField.setBounds(77, 25, 89, 20);
                jobInfo.add(keywordField);
                JButton addKeywordButton = new JButton("+ Add to Search");
                addKeywordButton.setBounds(170, 24, 115, 23);
                jobInfo.add(addKeywordButton);
                JLabel keywordLabel = new JLabel("Keyword:");
                keywordLabel.setBounds(20, 28, 59, 14);
                jobInfo.add(keywordLabel);
                
                jobTypeComboBox = new JComboBox<>(new String[]{"Any", "From List"});
                jobTypeComboBox.setBounds(277, 110, 102, 20);
                jobInfo.add(jobTypeComboBox);
                
                JLabel jobTypeLabel = new JLabel("Matching Jobs:");
                jobTypeLabel.setBounds(195, 113, 72, 14);
                jobInfo.add(jobTypeLabel);
                
                jobSpinner = new JSpinner();
                jobSpinner.setBounds(481, 156, 50, 19);
                jobInfo.add(jobSpinner);
                jobSpinner.setValue(10);
                
                
                JLabel lblJobLimit = new JLabel("Job Limit:");
                lblJobLimit.setBounds(417, 160, 56, 14);
                jobInfo.add(lblJobLimit);
                
                JLabel lblJobKeywords = new JLabel("Search keywords");
                lblJobKeywords.setFont(new Font("Tahoma", Font.BOLD, 11));
                lblJobKeywords.setBounds(404, 12, 99, 14);
                jobInfo.add(lblJobKeywords);
                
          
                
                JButton addBlackListBtn = new JButton("+ Add to BL");
                addBlackListBtn.setBounds(290, 24, 89, 23);
                jobInfo.add(addBlackListBtn);
                
                JLabel lblBlacklistKeywords = new JLabel("BlackList keywords");
                lblBlacklistKeywords.setFont(new Font("Tahoma", Font.BOLD, 11));
                lblBlacklistKeywords.setBounds(399, 82, 115, 14);
                jobInfo.add(lblBlacklistKeywords);
                
                JScrollPane blkeywords = new JScrollPane();
                blkeywords.setOpaque(false);
                blkeywords.setBounds(389, 101, 142, 48);
                jobInfo.add(blkeywords);
                
                           
                           searchTable= new JTable();
                           searchkeywords.setViewportView(searchTable);
                           searchTable.getTableHeader().setReorderingAllowed(false);
                           searchTable.getTableHeader().setResizingAllowed(false);
                           
         		
        		
        		
        		blTable= new JTable();
        		blkeywords.setViewportView(blTable);
        		blTable.getTableHeader().setReorderingAllowed(false);
        		blTable.getTableHeader().setResizingAllowed(false);
        		
         		
        		Headline = new JTextField(20);
        		Headline.setBounds(77, 54, 89, 20);
        		jobInfo.add(Headline);
        		
        		JLabel lblHeadline = new JLabel("Headline:");
        		lblHeadline.setBounds(20, 57, 59, 14);
        		jobInfo.add(lblHeadline);
        		
        		Summary = new JTextField(20);
        		Summary.setBounds(264, 54, 115, 20);
        		jobInfo.add(Summary);
        		
        		JLabel lblHeadline_1 = new JLabel("Summary:");
        		lblHeadline_1.setBounds(195, 57, 59, 14);
        		jobInfo.add(lblHeadline_1);
        		
        		Cover = new JTextField(20);
        		Cover.setBounds(264, 79, 115, 20);
        		jobInfo.add(Cover);
        		
        		JLabel lblHeadline_1_1 = new JLabel("Cover letter:");
        		lblHeadline_1_1.setBounds(192, 82, 69, 14);
        		jobInfo.add(lblHeadline_1_1);
        		
        		noticePeriod = new JTextField(20);
        		noticePeriod.setBounds(89, 78, 77, 20);
        		jobInfo.add(noticePeriod);
        		
        		JLabel lblHeadline_1_1_1 = new JLabel("notice period:");
        		lblHeadline_1_1_1.setBounds(20, 81, 79, 14);
        		jobInfo.add(lblHeadline_1_1_1);
        		
        		currentSalary = new JTextField(20);
        		currentSalary.setText("EGP");
        		currentSalary.setBounds(99, 109, 67, 20);
        		jobInfo.add(currentSalary);
        		
        		JLabel lblHeadline_1_1_1_1 = new JLabel("Current Salary:");
        		lblHeadline_1_1_1_1.setBounds(20, 110, 99, 14);
        		jobInfo.add(lblHeadline_1_1_1_1);
        		addKeywordButton.addActionListener(new ActionListener() {
        		    @Override
        		    public void actionPerformed(ActionEvent e) {
        		        String keyword = keywordField.getText().trim();
        		        if (!keyword.isEmpty()) {
        		      	    String[] searchword = {keyword};
        		      	    searchmodel.addRow(searchword);
        		      	    
        		      	    keywordField.setText("");
        		            searchKeyWords[keyWordsCounter]=keyword;
        		            keyWordsCounter++;
        		        }
        		    }
        		});
        		addBlackListBtn.addActionListener(new ActionListener() {
        		    @Override
        		    public void actionPerformed(ActionEvent e) {
        		        String keyword = keywordField.getText();
        		        if (!keyword.isEmpty()) {
        		      	    String[] blword = {keyword};
        		      	    blmodel.addRow(blword);
        		        	
        		      	  keywordField.setText("");
        		            blackListKeyWords[blackListCounter]=keyword;
        		            blackListCounter++;
        		        }
        		    }
        		});
        		
        		
        	    stats = new JLabel("Stats:");
        	    stats.setFont(new Font("Tahoma", Font.BOLD, 12));
        	    stats.setBounds(20, 160, 359, 21);
        	    jobInfo.add(stats);
        	    
        	        	    expectedSalary = new JTextField(20);
        	        	    expectedSalary.setText("EGP");
        	        	    expectedSalary.setBounds(107, 135, 59, 20);
        	        	    jobInfo.add(expectedSalary);
        	        	    
        	        	    JLabel lblHeadline_1_1_1_1_1 = new JLabel("Expected Salary:");
        	        	    lblHeadline_1_1_1_1_1.setBounds(20, 136, 99, 14);
        	        	    jobInfo.add(lblHeadline_1_1_1_1_1);
        	  
                
     

          		searchmodel=new DefaultTableModel();
        		Object[] searchColumn = {"Search Keyword"};
        		searchmodel.setColumnIdentifiers(searchColumn);

          		//filling jobs table
          	    String[] searchword = {""};
          	//    searchmodel.addRow(searchword);


          		blmodel=new DefaultTableModel();
        		Object[] blColumn = {"Blacklist Keyword"};
        		blmodel.setColumnIdentifiers(blColumn);

          		//filling jobs table
          	    String[] blword = {""};
          	   // blmodel.addRow(blword);

        
   
        
        settings.setBounds(0, 0, 140, 198);
        frame.getContentPane().add(settings);
        settings.setLayout(null);
        
        jobInfoBtn = new JButton("Job Info");
        jobInfoBtn.setBounds(15, 55, 103, 29);
        jobInfoBtn.setOpaque(false);
        settings.add(jobInfoBtn);
        
        personalInfoBtn = new JButton("Personal Info"); 
        personalInfoBtn.setBounds(15, 99, 103, 29);
        personalInfoBtn.setOpaque(false);

        settings.add(personalInfoBtn);
        
        JButton resultsBtn = new JButton("Results");
        resultsBtn.setBounds(15, 145, 103, 29);
        resultsBtn.setOpaque(false);
        settings.add(resultsBtn);
        
        homeBtn = new JButton("Home");
        homeBtn.setOpaque(false);
        homeBtn.setBounds(15, 10, 103, 29);
        settings.add(homeBtn);
        
        resultPanel = new JPanel();
        resultPanel.setBounds(130, 0, 553, 187);
        frame.getContentPane().add(resultPanel);
        resultPanel.setLayout(null);
        resultPanel.setVisible(false);
        
        
        JScrollPane jobsScroll = new JScrollPane();
        jobsScroll.setOpaque(false);
        jobsScroll.setBounds(22, 35, 515, 146);
        resultPanel.add(jobsScroll);
        jobsScroll.setOpaque(false);
  		
        resultTable = new JTable();
  		jobsScroll.setViewportView(resultTable);
  		resultTable.getTableHeader().setReorderingAllowed(false);
  		resultTable.getTableHeader().setResizingAllowed(false);


  		resultModel=new DefaultTableModel();
		Object[] resultColumn = {"No","Job Title","Company"};
	    resultModel.setColumnIdentifiers(resultColumn);
        
  		resultTable.setModel(resultModel);
  		
  
  		
        JLabel lblNewLabel = new JLabel("Enrolled Jobs");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(208, 11, 246, 19);
        resultPanel.add(lblNewLabel);
        resultPanel.setVisible(false);        

        
                personalInfoBtn.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                			jobInfo.setVisible(false);
                			resultPanel.setVisible(false);
                			personalInfo.setVisible(true);
                			createdBy.setVisible(false);
                    }
                });
                jobInfoBtn.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                			personalInfo.setVisible(false);
                			resultPanel.setVisible(false);
                			jobInfo.setVisible(true);
                			createdBy.setVisible(false);

                	}
                });
                
                
                resultsBtn.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
            			personalInfo.setVisible(false);
            			jobInfo.setVisible(false);
            			resultPanel.setVisible(true);
            			createdBy.setVisible(false);

                	}
                });
                
                homeBtn.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {

            			jobInfo.setVisible(false);
            			resultPanel.setVisible(false);
            			personalInfo.setVisible(false);
            			createdBy.setVisible(true);
                
                	}
                });
                try {
					path = new File(".").getCanonicalPath();
				} catch (IOException e1) {}
         		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(path+"\\Settings\\linkedin.png"));
           	    //problem
        	    searchTable.setModel(searchmodel);
        	    blTable.setModel(blmodel);
        		
        
        frame.setVisible(true);



    }
 
    private int jobcounter=1,pageCount=2;
    private String FirstName="",LastName="",City="",Phone="",Pronouns="",Englishlvl="",path="",exp="",profile="",summary="",cover="",headline="",currentSalary2="",excpecteSalary2="",period="",filePath="",resumePath="",photoPath="",edgeLocation="",profileName="";
    private String [] userSavedData=new String[16];
	private boolean noCv=true;
	static  String jobTitle="",companyName="";
	private JTextField expectedSalary;
    private String []answerdQestions=new String[1000];
	private int aQS=0;
	
  	
    //Main Method
    private void startAutomation() {
        try {
   
         	
        	resumePath=path+"\\Settings\\cv.pdf";
         	
        	
           	//initialize edge
            path = new File(".").getCanonicalPath();
            
            filePath = path+"\\Settings\\userConfig.txt"; 

            cvSections=  extractSections(readPDF(resumePath));
            
            languagesMap = extractLanguages(cvSections.getOrDefault("Languages", ""));
            
            
            
            setEdgeProfile();
            
            stats.setText("Launching Edge");

         	System.setProperty("webdriver.edge.driver", path+"/Settings/msedgedriver.exe");
        	
        	EdgeOptions	edgeOptions=new EdgeOptions();
        	edgeOptions.addArguments("--user-data-dir=" + edgeLocation);
        	edgeOptions.addArguments("--profile-directory=" + profileName);
            driver =new EdgeDriver(edgeOptions);
 
 
 
            File file = new File(filePath);
            if (file.exists()) {
                stats.setText("Reading Saved Value");

            	//take the saved setting
            	readValues();
            	
            	//set it
            	setValues();
               
            } else {
                try {
                	file.createNewFile(); 
                	//print the setting
                	 values();
                	 
                     stats.setText("Printing Values");
                	 
                	 printValues();
                	 
                	} catch (IOException e) {}
                 }  
                
         
            
            //get the job limit 
            int JobLimit=(int) jobSpinner.getValue();
            
            //loop to apply for limited number of jobs
            for(int s=1;s<=JobLimit;s++) {
            	try {
            		
                    stats.setText("Searching For job num "+s);

            		
            //search for recommended jobs
            Navigate("https://www.linkedin.com/jobs/collections/easy-apply");
     
            //click on each job
            jobSelect();
            
            blackList();
            
            
            elementIdentify();
 

            //store the job data for the user
      	    String[] job = {""+s,jobTitle,companyName};
      	    resultModel.addRow(job);
            
            
      	    
      	    
            	}catch (Exception ex) {} 
            }
            
            
            
           }catch (Exception ex) {} finally {
            SwingUtilities.invokeLater(() -> runAutomationButton.setEnabled(true));  
        }
    }

    //values for variables
    private void values() throws InterruptedException {
    
    //personal info
   	 FirstName=firstname.getText();
   	 LastName=lastname.getText();
   	 City=city.getText();
   	 Phone=phone.getText();
     exp=yearsOfExp.getText();
     profile=linkedIn.getText();
     cover=Cover.getText();
     summary=Summary.getText();
     headline=Headline.getText();
     currentSalary2=currentSalary.getText();
     excpecteSalary2=expectedSalary.getText();

     period=noticePeriod.getText();

     
   	//Male,Female
     Pronouns=(String) pronounsComboBox.getSelectedItem();
       
    //"Professional", "Native or bilingual", "Conversational", "None"
     Englishlvl=(String) englishProficiencyComboBox.getSelectedItem();
       
    }
    
    private void printValues() throws UnsupportedEncodingException, IOException {

		 OutputStream outputStream1 = new FileOutputStream(filePath, true);
	   try ( OutputStreamWriter Bwritecookie = new OutputStreamWriter(outputStream1, "UTF-8")) {
			   Bwritecookie.write(FirstName+":"+LastName+":"+City+":"+Phone+":"+exp+":"+profile+":"+cover+":"+summary+":"+headline+":"+currentSalary2+":"+excpecteSalary2+":"+period+":"+Pronouns+":"+Englishlvl+":"+resumePath+":"+photoPath);  								
			   Bwritecookie.flush();
			   Bwritecookie.close();}
   	 
    }
    
    
    private void readValues() throws IOException {
		 BufferedReader in  = new BufferedReader(new InputStreamReader( new FileInputStream(filePath), "utf-8"));
		 BufferedReader br=new BufferedReader(in);
		 String line= br.readLine();
		 
		 int intialValue=0;
		 
		 StringTokenizer tokenizer = new StringTokenizer(line, "$");
	        while (tokenizer.hasMoreTokens()) {
	        	userSavedData[intialValue]=tokenizer.nextToken();
	        	intialValue++;
	        }
        
    }
    
    
    private void setValues() throws InterruptedException {

       	 FirstName=userSavedData[0];
       	 LastName=userSavedData[1];
       	 City=userSavedData[2];
       	 Phone=userSavedData[3];
         exp=userSavedData[4];
         profile=userSavedData[5];
         cover=userSavedData[6];
         summary=userSavedData[7];
         headline=userSavedData[8];
         currentSalary2=userSavedData[9];
         excpecteSalary2=userSavedData[10];
         period=userSavedData[11];
         Pronouns=userSavedData[12];
         Englishlvl=userSavedData[13];
         resumePath=userSavedData[14];
         photoPath=userSavedData[15];
         
         
       	 firstname.setText(FirstName);;
       	 lastname.setText(LastName);
       	 city.setText(City);
       	 phone.setText(Phone);
         yearsOfExp.setText(exp);
         linkedIn.setText(profile);
         Cover.setText(cover);
         Summary.setText(summary);
         Headline.setText(headline);
         currentSalary.setText(currentSalary2);
         expectedSalary.setText(excpecteSalary2);
         noticePeriod.setText(period);

        }
    
    
    //search function
    private void Navigate(String url) throws InterruptedException {  
    	
        if (driver != null) {
            driver.get(url); 
            TimeUnit.SECONDS.sleep(3);
        } else {
            System.out.println("Error: WebDriver is not initialized.");
        }
    }
    
 
    
    
    public static boolean isQuestionAsked(String question) {
        return askedQuestions.contains(question);
    }
    
    public static void clearAskedQuestions() {
        askedQuestions.clear(); 
    }
    
    //select a job function
    private void jobSelect() throws InterruptedException { 
    	
    boolean foundEasyApply=false;
    boolean pageEnded=true;

    stats.setText("Choosing The job Card num "+jobcounter);

    
    
    try{clearAskedQuestions();}catch (Exception ex) {}
    
    
     //job card
    for(int z=0;z<2;z++) {
    try {driver.findElementByXPath("(//div[@data-view-name='job-card'])["+jobcounter+"]").click(); pageEnded=false; z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
    }
    
    
    if(pageEnded==true) {
    	
        stats.setText("No jobs in the Current Page ");

    	   for(int z=0;z<5;z++) {
    		    try {driver.findElementByXPath("//button[@aria-label='Page "+pageCount+"']").click(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
    		    }
    	   pageCount++;
    	   
           stats.setText("Navigate to page num "+pageCount);

    	   
    	   
    	     //job card
    	    for(int z=0;z<5;z++) {
    	    try {driver.findElementByXPath("(//div[@data-view-name='job-card'])["+jobcounter+"]").click(); pageEnded=false; z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
    	    }
    	    
    	    stats.setText("Choosing The job Card num "+jobcounter);

    }
    
    //check for blacklist
    boolean isBlackListed=false;
    boolean isSearchListed=false;

    String jobSearchChoice=(String) jobTypeComboBox.getSelectedItem();
    
    if(jobSearchChoice.equalsIgnoreCase("any")) {
		//no prefernce
    }else {
        stats.setText("Checking if the job blackListed ");

    	
    isBlackListed=blackList();
    
    stats.setText(""+isBlackListed);

    
    }           

    
    
    if(isBlackListed==true) {
    	 stats.setText("BlackListed get another job");
    	
    	jobcounter++;
    	jobSelect();
    	
    }else {
    	if(jobSearchChoice.equalsIgnoreCase("any")) {
    		
    		 stats.setText("Looking for easy apply");
    		 
            //click on easy apply
            for(int z=0;z<2;z++) {
            try {driver.findElementByXPath("(//button[contains(.,'Easy Apply')])[1]").click(); foundEasyApply=true; z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
            }
    
            stats.setText("Looking for special button ");
            
            for(int z=0;z<2;z++) {
                try {driver.findElementByXPath("//button[contains(.,'Continue applying')]").click(); foundEasyApply=true; z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
                } 
            
    	}else {
    		 stats.setText("Is the job from search list ");
    		 
    		isSearchListed=searchList();
    		
    		 stats.setText(""+isSearchListed);
    		 
    		if(isSearchListed==false) {
    			//not on search list
    		}else {
       		 stats.setText("Looking for easy apply");

    	        //click on easy apply
    	        for(int z=0;z<2;z++) {
    	        try {driver.findElementByXPath("(//button[contains(.,'Easy Apply')])[1]").click(); foundEasyApply=true; z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
    	        }
    	        
                stats.setText("Looking for special button ");

    	        for(int z=0;z<2;z++) {
    	            try {driver.findElementByXPath("//button[contains(.,'Continue applying')]").click(); foundEasyApply=true; z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
    	            } 
    	        
    		}
    	}
 
    
    
    jobcounter++;

    if(foundEasyApply==false) {
        stats.setText("This job isn't a easy apply one");

    	jobSelect();
    }
     
    }
     
    }
    
    private void basicInfoFiller() throws InterruptedException {  
        
        //Find Personal info to fill 
    	
        List<WebElement> labels=null;
        try{labels= driver.findElements(By.xpath("//div[contains(@class, 'jobs-easy-apply-modal')]//label[@class='artdeco-text-input--label']"));
         
        for (int i = 0; i < labels.size(); i++) {
           
        	try {
        	String labelText = labels.get(i).getText().trim();
        	
        	if (isQuestionAsked(labelText)) {
               
             }else {
        	
        	String dataToFill =getBestAnswer(labelText);
        	askedQuestions.add(labelText);
            
            stats.setText("Data is "+dataToFill);
            
            if (!dataToFill.equals("idk")) {
            	
            	 boolean foundElement=false;
                 
                 try {driver.findElementByXPath("//textarea[@aria-label='"+labelText+"']").clear(); }catch (Exception ex) {}
                 try {driver.findElementByXPath("//textarea[@aria-label='"+labelText+"']").sendKeys(dataToFill); foundElement=true;}catch (Exception ex) {}

                 if(foundElement==false) {
                	
                WebElement inputField = driver.findElement(By.xpath("(//div[contains(@class, 'jobs-easy-apply-modal')]//label[@class='artdeco-text-input--label'])[" + (i + 1) + "]/following::input[@type='text'][1]"));
                inputField.clear();
                inputField.sendKeys(dataToFill);
                 
                    }
                 
                 
                 }
             }   }catch (Exception ex) {}
        }
    	
    	
    	
    	boolean onetimecity=false;
    	

  
             
             
             
    	boolean visOfElementCity=false;

    if(onetimecity==false) {
    for(int z=0;z<1;z++) {
    try {visOfElementCity=driver.findElementByXPath("//label[contains(.,'Location (city)Location (city)')]").isDisplayed(); z=10;}catch (Exception ex) {}
    }
       }
    

        
        if(visOfElementCity==true) {
        	String DataToFill="";
        	 DataToFill =getBestAnswer("City");

 
            for(int z=0;z<5;z++) {
            try {driver.findElementByXPath("(//input[contains(@role,'combobox')])[1]").sendKeys(DataToFill); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
            }
            
            for(int z=0;z<2;z++) {
            try {driver.findElementByXPath("(//div[contains(@class,'hit--autocomplete')])[1]").click(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
            }
            onetimecity=true;
        }
        
      
        
        }catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}

    	

    }
    
    private void SkillsFiller() throws InterruptedException {  
  

         //Find Personal info to fill
        List<WebElement> labels=null;
        try{labels= driver.findElements(By.xpath("//div[contains(@class, 'jobs-easy-apply-modal')]//span[@aria-hidden='true']"));
        
         int k=1;
        for (int i = 0; i < labels.size(); i++) {
        	
        	try {
            String labelText = labels.get(i).getText().trim();

         	String dataToFill =getBestAnswer(labelText);

        	if (isQuestionAsked(labelText)) {
                
            }else {
             askedQuestions.add(labelText);
            	
            stats.setText("Data is "+dataToFill);
            
           {

                //1 time exception
                 if(labelText.contains("experience")||labelText.contains("years")) {
           		 for(int z=0;z<2;z++) {
                     try {driver.findElementByXPath("(//div[@data-test-text-selectable-option='1'])[2]").click(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
                     }
                }
                
                   if(labelText.contains("Phone")||labelText.contains("Email")) {}else {

                /*	
                	try{
                    WebElement inputField = driver.findElement(By.xpath("(//div[contains(@class, 'jobs-easy-apply-modal')]//label[@class='artdeco-text-input--label'])[" + (i + 1) + "]/following::select[@aria-required='true'][1]"));
                    inputField.click();
                    
                    inputField = driver.findElement(By.xpath("(//div[contains(@class, 'jobs-easy-apply-modal')]//label[@class='artdeco-text-input--label'])[" + (i + 1) + "]/following::option[contains(.,'"+dataToFill+"'][1]"));
                    inputField.click();
                }catch (Exception ex) {}
                    
                   	try{
                        WebElement inputField = driver.findElement(By.xpath("(//div[contains(@class, 'jobs-easy-apply-modal')]//label[@class='artdeco-text-input--label'])[" + (i + 1) + "]/following::select[@aria-required='true'][1]"));
                        inputField.click();
                    }catch (Exception ex) {}
                   	
                     */
                	
                	for(int z=0;z<1;z++) {
                	
                 	try{
                    WebElement inputField = driver.findElement(By.xpath("(//div[contains(@class, 'jobs-easy-apply-modal')]//span[@aria-hidden='true'])["+k+"]/following::select[@aria-required='true'][1]"));
                        inputField.click();  
                        
                    }catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
                   	
                 	 
                 	
                  	
                 			
                 	if(labelText.contains("do you")||labelText.contains("are you")||labelText.contains("have you")) {
                 	 
                 		
                 		if(dataToFill.equalsIgnoreCase("No")||dataToFill.equalsIgnoreCase("no")||dataToFill.equalsIgnoreCase("")) {
                 	 		  try {driver.findElement(By.xpath("(//option[contains(.,'No')])["+k+"]")).click(); }catch (Exception ex) {}
                 	 		try {driver.findElement(By.xpath("(//input[@value='no'])["+k+"]")).click(); }catch (Exception ex) {}
                        try {driver.findElement(By.xpath("(//input[contains(@type,'checkbox')])[2]")).click(); }catch (Exception ex) {}
                        try {driver.findElement(By.xpath("(//label[contains(.,'No')])["+k+"]")).click(); }catch (Exception ex) {}

                 	 	}else {
                    	    	 
                    	    	
                      	 		try {driver.findElement(By.xpath("(//option[contains(.,'Yes')])["+k+"]")).click(); }catch (Exception ex) {}
                                try {driver.findElement(By.xpath("(//label[contains(.,'Yes')])["+k+"]")).click(); }catch (Exception ex) {}
                             try {driver.findElement(By.xpath("(//input[contains(@type,'checkbox')])[1]")).click(); }catch (Exception ex) {}
                  	 		try {driver.findElement(By.xpath("(//input[@value='yes'])["+k+"]")).click(); }catch (Exception ex) {}
                    	    	 
                      	 	}
                  		 
                 	}
                 	 
                 	else	if(dataToFill.contains("yes")||dataToFill.contains("Yes")||!dataToFill.contains("")||!labelText.contains("notice")) {
                        try {driver.findElement(By.xpath("(//label[contains(.,'Yes')])["+k+"]")).click(); }catch (Exception ex) {}
              	 		try {driver.findElement(By.xpath("(//option[contains(.,'Yes')])["+k+"]")).click(); }catch (Exception ex) {}
                     try {driver.findElement(By.xpath("(//input[contains(@type,'checkbox')])[1]")).click(); }catch (Exception ex) {}
          	 		try {driver.findElement(By.xpath("(//input[@value='yes'])["+k+"]")).click(); }catch (Exception ex) {}
                 		
               	 	}
            	     else {

              	 		try {driver.findElement(By.xpath("(//option[contains(.,'No')])["+k+"]")).click(); }catch (Exception ex) {}
                     try {driver.findElement(By.xpath("(//input[contains(@type,'checkbox')])[2]")).click(); }catch (Exception ex) {}
          	 		try {driver.findElement(By.xpath("(//input[@value='no'])["+k+"]")).click(); }catch (Exception ex) {}
                    try {driver.findElement(By.xpath("(//label[contains(.,'No')])["+k+"]")).click(); }catch (Exception ex) {}

            	     }
                 	
                 	TimeUnit.SECONDS.sleep(1);
                	}
                	 
                  	

          	 		try{
                        WebElement inputField = driver.findElement(By.xpath("(//div[contains(@class, 'jobs-easy-apply-modal')]//span[@aria-hidden='true'])[1]"));
                        inputField.click();  
                    }catch (Exception ex) {}
                	
                    if(labelText.contains("English")||labelText.contains("english")) {

                    	
                     	try{
                        WebElement inputField = driver.findElement(By.xpath("(//div[contains(@class, 'jobs-easy-apply-modal')]//span[@aria-hidden='true'])["+k+"]/following::select[@aria-required='true'][1]"));
                        inputField.click();  
                            
                        }catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
                     	
                    	if(dataToFill.equalsIgnoreCase("Native or bilingual"))
                    	{
                            for(int z=0;z<2;z++) {
                                try {driver.findElementByXPath("//input[@data-test-text-selectable-option__input='Fluent']").click(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
                                }
                    	}else if(dataToFill.equalsIgnoreCase("None"))
                    	{
                    		 for(int z=0;z<2;z++) {
                                 try {driver.findElementByXPath("//input[@data-test-text-selectable-option__input='Basic']").click(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
                                 }
                    	}
                    	else {
                    		 for(int z=0;z<2;z++) {
                                 try {driver.findElementByXPath("//input[@data-test-text-selectable-option__input='"+dataToFill+"']").click(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
                                 }
                    	}
          
                    }
             
            }
        }
            }}catch (Exception ex) {}
        	
        	k++;
        }
		 
   
        }catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}

        
        
    	  }

    
    
    
    
     
    
    private void ResumeUploader() throws InterruptedException {
    	
      //to prevent uploading alot of cvs
    	if(noCv==false) {
    	
     boolean visOfElement=false;
     for(int z=0;z<2;z++) {
      try {visOfElement=driver.findElementByXPath("//span[contains(.,'Upload resume')]").isDisplayed(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
    }
     
     if(visOfElement==true) {
         for(int z=0;z<5;z++) {
         try {driver.findElementByXPath("//input[@name='file']").sendKeys(""+resumePath); noCv=true; TimeUnit.SECONDS.sleep(10); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
         
         }
     }
    	}
    }
    
    private void PhotoUploader() throws InterruptedException {
    	
 
       boolean visOfElement=false;
       for(int z=0;z<2;z++) {
        try {visOfElement=driver.findElementByXPath("//span[contains(.,'Photo')]").isDisplayed(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
      }
       
       if(visOfElement==true) {
           for(int z=0;z<5;z++) {
           try {driver.findElementByXPath("//input[@name='file']").sendKeys(""+photoPath); TimeUnit.SECONDS.sleep(10); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
           
           }
       }
      	
      }
    
    
    private void setEdgeProfile() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader( new FileInputStream(path+"\\Settings\\EdgeProfile.txt"), "utf-8"));
    	BufferedReader br=new BufferedReader(in);
    	edgeLocation = br.readLine();		
    	profileName = br.readLine();		

 		 try {
    	        String command2 = "taskkill /F /IM msedgedriver.exe";
    	        String command3 = "taskkill /F /IM msedge.exe";


    	        
    		    StringBuffer output1 = new StringBuffer();
    	         Process SerNumProcess = null;
    	      
    			try {SerNumProcess = Runtime.getRuntime().exec(command2);} catch (IOException e2) {e2.printStackTrace();}
    	        Thread.sleep(500);
    			try {SerNumProcess = Runtime.getRuntime().exec(command3);} catch (IOException e2) {e2.printStackTrace();}
    			 Thread.sleep(500); 
 
    	        } catch(Exception ev12) {}  
 		 
 		 
    }
    
    
    private static boolean blackList() throws InterruptedException {
     	   boolean isBlackListed=false;
     	   
    	for(int z=0;z<1;z++) {
    		    try {jobTitle=driver.findElementByXPath("(//a[contains(@href,'landing')])[1]").getText(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
    		    try {jobTitle=driver.findElementByXPath("(//a[@class='ember-view'])[1]").getText(); z=10;}catch (Exception ex) {}
    		    }
    	 
	    try {companyName=driver.findElementByXPath("(//a[@target='_self'])[6]").getText();}catch (Exception ex) {}
	    jobTitle=  jobTitle.toLowerCase();
	    
	    
    	for(int i=0;i<blackListCounter;i++) {
    		if(jobTitle.contains(blackListKeyWords[i])) {
    			isBlackListed=true;
    			return isBlackListed;
    		}
    	}
    	 return  isBlackListed;
    }
    
    private static boolean searchList() throws InterruptedException {
  	   boolean isSearchListed=false;

 	jobTitle=  jobTitle.toLowerCase();
 	
if(keyWordsCounter<=1) {
	isSearchListed=true;
	 return isSearchListed;
}else {
 	for(int i=0;i<keyWordsCounter;i++) {
 	
 		if(jobTitle.contains(searchKeyWords[i])) {
 			isSearchListed=true;
 			 return isSearchListed;
 		}
 	}
 	  }
 	
 	
 	
 	 return  isSearchListed;
 }
 
    
    private void elementIdentify() throws InterruptedException {
    	
    	boolean submitVisable=false;
    	 
    	// we should calculate how many basic fields we have and how many skills fields we have and check if we have a resume or photo button then we call all the functions based on 
    	// how many fields we have for each and send to it the number of the fields it will act upon it
    	
    	while(submitVisable==false) {
    	      //click to Review
            for(int z=0;z<1;z++) {
            try {submitVisable=driver.findElementByXPath("//button[contains(.,'Submit application')]").isDisplayed(); z=10;}catch (Exception ex) {}

            }
    		
            stats.setText("Review is shown ? "+submitVisable);
 
        	boolean visOfElement=false,visOfElement2=false,visOfElement3=false,visOfElement4=false,noElements=false;
    
        	
        	
        	   for(int z=0;z<1;z++) {
        		   stats.setText("Looking for Add more "+z);
        		   
           	    try {noElements=driver.findElementByXPath("//button[contains(.,'Add more')]").isDisplayed(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
           	    }
        	   
           	    if(noElements==false) {
        	   
        	   
    	    for(int z=0;z<2;z++) {
    	  
    	        List<WebElement> labels=null;
    	        List<WebElement> labels2=null;
    	        try{labels= driver.findElements(By.xpath("//div[contains(@class, 'jobs-easy-apply-modal')]//label[@class='artdeco-text-input--label']"));}catch (Exception ex) {}
    	        try{labels2= driver.findElements(By.xpath("//div[contains(@class, 'jobs-easy-apply-modal')]//span[@aria-hidden='true']"));}catch (Exception ex) {}
    	        
    	        if(labels.size()>0) {
    	        	visOfElement=true;
    	        }
    	        
    	        if(labels2.size()>0) {
    	        	visOfElement2=true;
    	        }
 
           
    	        
    	    try {visOfElement3=driver.findElementByXPath("//span[contains(.,'Photo')]").isDisplayed(); }catch (Exception ex) {}
    	    try {visOfElement4=driver.findElementByXPath("//span[contains(.,'Upload resume')]").isDisplayed(); }catch (Exception ex) {}
    	    }
    	    
    	    stats.setText("basic ="+visOfElement+" & Slills ="+visOfElement2+" &Photo="+visOfElement3+" &Resume="+visOfElement4);

    	    
    	        if(visOfElement==true) {
    	        	basicInfoFiller();
    	        }
    	        if(visOfElement2==true) {
    	        	SkillsFiller();
    	        }
    		
    	        if(visOfElement3==true) {
    	        	//PhotoUploader();
    	        }
    	        
    	        if(visOfElement4==true) {
    	        	//ResumeUploader();
    	        }
    		
           	    }
 
        
        //click on Next to finish 1st page
        for(int z=0;z<2;z++) {
        try {driver.findElementByXPath("(//button[contains(.,'Next')])[1]").click(); z=10; }catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
        }

        for(int z=0;z<1;z++) {
            try {driver.findElementByXPath("//button[contains(.,'Review')]").click(); z=10;}catch (Exception ex) {}
            }

        
        //click on Next to finish 1st page
        for(int z=0;z<1;z++) {
        try {driver.findElementByXPath("//label[contains(.,'I Agree Terms & Conditions')]").click(); z=10; }catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
        }

         
    	         }
    	 

        //click to Review
        for(int z=0;z<3;z++) {
        try {driver.findElementByXPath("//button[contains(.,'Review')]").click(); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
        }

        
		   stats.setText("Done applying !");

        //click to finish
        for(int z=0;z<3;z++) {
           try {driver.findElementByXPath("//button[contains(.,'Submit application')]").click();TimeUnit.SECONDS.sleep(5); z=10;}catch (Exception ex) {TimeUnit.SECONDS.sleep(1);}
        }

 
        
    }
    

    

    private String readPDF(String filePath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }

    private Map<String, String> extractSections(String cvText) {
        Map<String, String> sections = new LinkedHashMap<>();
        Pattern sectionPattern = Pattern.compile("(?m)^(Profile|Education|Professional Experience|Courses|Skills|Languages)$"); 
        Matcher matcher = sectionPattern.matcher(cvText);

        List<Integer> sectionIndices = new ArrayList<>();
        List<String> sectionNames = new ArrayList<>();

        while (matcher.find()) {
            sectionIndices.add(matcher.start());
            sectionNames.add(matcher.group());
        }

        for (int i = 0; i < sectionNames.size(); i++) {
            int start = sectionIndices.get(i);
            int end = (i + 1 < sectionIndices.size()) ? sectionIndices.get(i + 1) : cvText.length();
            sections.put(sectionNames.get(i), cvText.substring(start, end).replace(sectionNames.get(i), "").trim());
        }

        return sections;
    }

    private Map<String, String> extractLanguages(String languagesText) {
        Map<String, String> languages = new HashMap<>();
        languagesText = languagesText.replaceAll("\\s*:\\s*", ":");
        String lines[] =languagesText.split(" ");
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    languages.put(parts[0].trim().toLowerCase(), parts[1].trim());
                }
            }
            return languages;
    }

    public String getLanguageProficiency(String language) {
        return languagesMap.getOrDefault(language.toLowerCase(), "Language not found.");
    }
    
    public String getBestAnswer(String question) {
        question = question.toLowerCase();
        
 
    	String result = "";

    	boolean finishedLoop=false;

        if (question.contains("experience")) {
        	if(question.contains("do you")&&!question.contains("how many")) {
        		
                String skillsSection = cvSections.getOrDefault("Skills", "").toLowerCase();
                String[] skills = skillsSection.split(",|\\n| ");
                for (String skill : skills) {
                	if(finishedLoop==false) {
                    if (!skill.trim().isEmpty() && question.contains(skill.trim())) {
                    	result= "Yes";                    	  
                    	finishedLoop=true;
                    }else {
                    	result= "No";
                    }
                	 }
                }
         	}
        	else if(question.contains("how many")) {
        		result= exp;
              
        	}
        	else {
        		result= summary;
        	}
        	
        } 
        else if(question.contains("notice")||question.contains("days")||question.contains("period")||question.contains("immediately")) {
        	result=period;
        }
        else if (question.contains("site")||question.contains("remote")||question.contains("part")){
        	result= "Yes";
        }
        else if(question.contains("start date")) {

    		int convert=Integer.parseInt(period);
        	result=calculateStartDate(convert);
        	
        }
        else if (question.contains("skills")) {
            String skills = cvSections.getOrDefault("Skills", "");
            result= skills.replace("\n", ", ").replaceAll("\\s*,\\s*", ", ");
        } 
        else if (question.contains("education") || question.contains("study")) {
        	result= cvSections.getOrDefault("Education", "");
        } 
        else if (question.contains("courses")||question.contains("certificate")) {
            String courses = cvSections.getOrDefault("Courses", "");
            result= courses.replace("\n", ", ").replaceAll("\\s*,\\s*", ", ");
        }  
        else if (question.contains("english")) {
        	result= getLanguageProficiency("english");

        }
        else if (question.contains("arabic")) {
        	result= getLanguageProficiency("arabic");

        }
        else if(question.contains("gender")||question.contains("sex")||question.contains("male")||question.contains("female")) {
        
        	result= Pronouns;
        }
        else if (question.contains("do you")) { 
        	
        	result= doYou(question);
        }else if(question.contains("why you")||question.contains("why")) {
        	result= summary;
        	 
        	
        }
    	
    	
    	else if(question.contains("experience")||question.contains("years")) {
            
        	if(question.contains("notice")||question.contains("days")||question.contains("period")) {
            	result=period;

        	}
        	else if(question.contains("how many")) 
        	{
            	result=exp;
        	}
        	
        }
    	else if(question.contains("phone")||question.contains("mobile")) {
        	result=Phone;
        }
    	else if(question.contains("laptop")) {
    		result="Yes";
    	}
        else if(question.contains("does the")) {
        	result="Yes it does.";
        }
        else if(question.contains("city")||question.contains("location")||question.contains("nationality")||question.contains("based")||question.contains("citizenship")||question.contains("citizen")||question.contains("locate")) {
        	result=City;
        }

        else if(question.contains("second name")||question.contains("last name")||question.contains("last")) {
        	result=LastName;
        }
        else if(question.contains("first name")) {
        	result=FirstName;
        }
        else if(question.contains("portfolio")||question.contains("linkedin")) {
        	result=profile;
        }
        else if(question.contains("headline")) {
        	result=headline;
        }
        else if(question.contains("summary")) {
        	result=summary;
        }
        else if(question.contains("cover letter")||question.contains("cover")||question.contains("your message")) {
        	result=cover;
        }
        else if(question.contains("salary")||question.contains("gross")||question.contains("desired")) {
    		String reqSalary="";

        	if(question.contains("current")) {
        		reqSalary=currentSalary2;
        	}else {
        		reqSalary=excpecteSalary2;
        	}
        		
        	if(question.contains("usd")||question.contains("eur")) {
        		int convert=Integer.parseInt(reqSalary);
        		convert=convert/50;
        		reqSalary=""+convert;
        		result=reqSalary;
        	}else if(question.contains("sar")) {
        		int convert=Integer.parseInt(reqSalary);
        		convert=convert/11;
        		reqSalary=""+convert;
        		result=reqSalary;
        	}else{
        	result=reqSalary;}
        }
        else if(question.contains("where")) {
        	result=City;
        	
        }
        else if(question.contains("tools")) {

            String skills = cvSections.getOrDefault("Skills", "");
            result= skills.replace("\n", ", ").replaceAll("\\s*,\\s*", ", ");
                	
        }
        else {
            double highestSimilarity = 0.0;
            String bestMatch = "No";
            
            List<String> allRelevantSections = new ArrayList<>();
            for (String key : cvSections.keySet()) {
                allRelevantSections.add(key + ": " + cvSections.get(key));
            }
            
            for (String section : allRelevantSections) {
                double similarity = calculateJaccardSimilarity(question, section);
                if (similarity > highestSimilarity) {
                    highestSimilarity = similarity;
                    bestMatch = section;
                }
            }
            result = highestSimilarity > 0.5 ? bestMatch : "No";      	
        }
        
        
 
        return result;

        
     }
    
    private double calculateJaccardSimilarity(String str1, String str2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(str1.split(" ")));
        Set<String> set2 = new HashSet<>(Arrays.asList(str2.split(" ")));
        
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        
        return (double) intersection.size() / union.size();
    }
    
    

    public static String calculateStartDate(int noticePeriodDays) {
        LocalDate today = LocalDate.now();

        LocalDate startDate = today.plusDays(noticePeriodDays);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return startDate.format(formatter);
    }
   
    private String doYou(String question) {

    	
        String skillsSection = cvSections.getOrDefault("Skills", "").toLowerCase();    
        String[] skills = skillsSection.split(",|\\n| ");
        
        String ProfessionalExperience = cvSections.getOrDefault("Professional Experience", "").toLowerCase();    
        String[] ProfessionalExperiences = ProfessionalExperience.split(",|\\n| ");
        
        String Profile = cvSections.getOrDefault("Profile", "").toLowerCase();    
        String[] Profiles = Profile.split(",|\\n| ");
        
        String Education = cvSections.getOrDefault("Education", "").toLowerCase();    
        String[] Educations = Education.split(",|\\n| ");
        
        String Courses = cvSections.getOrDefault("Courses", "").toLowerCase();    
        String[] Coursess = Courses.split(",|\\n| ");
        
        
        String Languages = cvSections.getOrDefault("Languages", "").toLowerCase();    
        String[] Languagess = Languages.split(",|\\n| ");
        
        for (String skill : skills) {
        	if(skill.trim().equalsIgnoreCase("have")||skill.trim().equalsIgnoreCase("has")||skill.trim().equalsIgnoreCase("is")||skill.trim().equalsIgnoreCase("and")
        			||skill.trim().equalsIgnoreCase("in")||skill.trim().equalsIgnoreCase("on")||skill.trim().equalsIgnoreCase("are")||skill.trim().equalsIgnoreCase("to")||skill.trim().equalsIgnoreCase("the")
        			||skill.trim().equalsIgnoreCase("of")||skill.trim().equalsIgnoreCase("by")||skill.trim().equalsIgnoreCase("this")||skill.trim().equalsIgnoreCase("that")||skill.trim().equalsIgnoreCase("they")
        			||skill.trim().equalsIgnoreCase("a")||skill.trim().equalsIgnoreCase("with")
        			||skill.trim().equalsIgnoreCase("for")||skill.trim().equalsIgnoreCase("using")||skill.trim().equalsIgnoreCase("use")||skill.trim().equalsIgnoreCase("used")||skill.trim().equalsIgnoreCase("via")) 
        	{
        		
        	}
        	
        	else if (!skill.trim().isEmpty()  && question.contains(skill.trim())) {
            	
                return skillsSection;
            }
        }
        
         
        
        for (String ProfessionalExperiences1 : ProfessionalExperiences) {
        	if(ProfessionalExperiences1.trim().equalsIgnoreCase("have")||ProfessionalExperiences1.trim().equalsIgnoreCase("has")||ProfessionalExperiences1.trim().equalsIgnoreCase("is")||ProfessionalExperiences1.trim().equalsIgnoreCase("and")
        			||ProfessionalExperiences1.trim().equalsIgnoreCase("in")||ProfessionalExperiences1.trim().equalsIgnoreCase("on")||ProfessionalExperiences1.trim().equalsIgnoreCase("are")||ProfessionalExperiences1.trim().equalsIgnoreCase("to")||ProfessionalExperiences1.trim().equalsIgnoreCase("the")
        			||ProfessionalExperiences1.trim().equalsIgnoreCase("of")||ProfessionalExperiences1.trim().equalsIgnoreCase("by")||ProfessionalExperiences1.trim().equalsIgnoreCase("this")||ProfessionalExperiences1.trim().equalsIgnoreCase("that")||ProfessionalExperiences1.trim().equalsIgnoreCase("they")
        			||ProfessionalExperiences1.trim().equalsIgnoreCase("a")||ProfessionalExperiences1.trim().equalsIgnoreCase("with")
        			||ProfessionalExperiences1.trim().equalsIgnoreCase("for")||ProfessionalExperiences1.trim().equalsIgnoreCase("using")||ProfessionalExperiences1.trim().equalsIgnoreCase("use")||ProfessionalExperiences1.trim().equalsIgnoreCase("used")||ProfessionalExperiences1.trim().equalsIgnoreCase("via")) 
        	{
        		
        	}
        	
        	else   if (!ProfessionalExperiences1.trim().isEmpty()  && question.contains(ProfessionalExperiences1.trim())) {
                return ProfessionalExperience;
            }
        }
        
        for (String Profiles1 : Profiles) {
         	if(Profiles1.trim().equalsIgnoreCase("have")||Profiles1.trim().equalsIgnoreCase("has")||Profiles1.trim().equalsIgnoreCase("is")||Profiles1.trim().equalsIgnoreCase("and")
        			||Profiles1.trim().equalsIgnoreCase("in")||Profiles1.trim().equalsIgnoreCase("on")||Profiles1.trim().equalsIgnoreCase("are")||Profiles1.trim().equalsIgnoreCase("to")||Profiles1.trim().equalsIgnoreCase("the")
        			||Profiles1.trim().equalsIgnoreCase("of")||Profiles1.trim().equalsIgnoreCase("by")||Profiles1.trim().equalsIgnoreCase("this")||Profiles1.trim().equalsIgnoreCase("that")||Profiles1.trim().equalsIgnoreCase("they")
        			||Profiles1.trim().equalsIgnoreCase("for")||Profiles1.trim().equalsIgnoreCase("using")||Profiles1.trim().equalsIgnoreCase("use")||Profiles1.trim().equalsIgnoreCase("used")||Profiles1.trim().equalsIgnoreCase("via")
        			||Profiles1.trim().equalsIgnoreCase("a")||Profiles1.trim().equalsIgnoreCase("with")) 
        	{
        		
        	}
        	
        	else  if (!Profiles1.trim().isEmpty() && question.contains(Profiles1.trim())) {
                return Profile;
            }
        }
        
        for (String Educations1 : Educations) {
        	if(Educations1.trim().equalsIgnoreCase("have")||Educations1.trim().equalsIgnoreCase("has")||Educations1.trim().equalsIgnoreCase("is")||Educations1.trim().equalsIgnoreCase("and")
        			||Educations1.trim().equalsIgnoreCase("in")||Educations1.trim().equalsIgnoreCase("on")||Educations1.trim().equalsIgnoreCase("are")||Educations1.trim().equalsIgnoreCase("to")||Educations1.trim().equalsIgnoreCase("the")
        			||Educations1.trim().equalsIgnoreCase("of")||Educations1.trim().equalsIgnoreCase("by")||Educations1.trim().equalsIgnoreCase("this")||Educations1.trim().equalsIgnoreCase("that")||Educations1.trim().equalsIgnoreCase("they")
        			||Educations1.trim().equalsIgnoreCase("a")||Educations1.trim().equalsIgnoreCase("with")
        			||Educations1.trim().equalsIgnoreCase("for")||Educations1.trim().equalsIgnoreCase("using")||Educations1.trim().equalsIgnoreCase("use")||Educations1.trim().equalsIgnoreCase("used")||Educations1.trim().equalsIgnoreCase("via")) 
        	{
        		
        	}
        	
        	else  if (!Educations1.trim().isEmpty()  && question.contains(Educations1.trim())) {
                return Education;
            }
        }
        
        for (String Coursess1 : Coursess) {
        	if(Coursess1.trim().equalsIgnoreCase("have")||Coursess1.trim().equalsIgnoreCase("has")||Coursess1.trim().equalsIgnoreCase("is")||Coursess1.trim().equalsIgnoreCase("and")
        			||Coursess1.trim().equalsIgnoreCase("in")||Coursess1.trim().equalsIgnoreCase("on")||Coursess1.trim().equalsIgnoreCase("are")||Coursess1.trim().equalsIgnoreCase("to")||Coursess1.trim().equalsIgnoreCase("the")
        			||Coursess1.trim().equalsIgnoreCase("of")||Coursess1.trim().equalsIgnoreCase("by")||Coursess1.trim().equalsIgnoreCase("this")||Coursess1.trim().equalsIgnoreCase("that")||Coursess1.trim().equalsIgnoreCase("they")
        			||Coursess1.trim().equalsIgnoreCase("a")||Coursess1.trim().equalsIgnoreCase("with")
        			||Coursess1.trim().equalsIgnoreCase("for")||Coursess1.trim().equalsIgnoreCase("using")||Coursess1.trim().equalsIgnoreCase("use")||Coursess1.trim().equalsIgnoreCase("used")||Coursess1.trim().equalsIgnoreCase("via")) 
        	{
        		
        	}
        	
        	else if (!Coursess1.trim().isEmpty()  && question.contains(Coursess1.trim())) {
                return Courses;
            }
        }
        
        for (String Languagess1 : Languagess) {
        	if(Languagess1.trim().equalsIgnoreCase("have")||Languagess1.trim().equalsIgnoreCase("has")||Languagess1.trim().equalsIgnoreCase("is")||Languagess1.trim().equalsIgnoreCase("and")
        			||Languagess1.trim().equalsIgnoreCase("in")||Languagess1.trim().equalsIgnoreCase("on")||Languagess1.trim().equalsIgnoreCase("are")||Languagess1.trim().equalsIgnoreCase("to")||Languagess1.trim().equalsIgnoreCase("the")
        			||Languagess1.trim().equalsIgnoreCase("of")||Languagess1.trim().equalsIgnoreCase("by")||Languagess1.trim().equalsIgnoreCase("this")||Languagess1.trim().equalsIgnoreCase("that")||Languagess1.trim().equalsIgnoreCase("they")
        			||Languagess1.trim().equalsIgnoreCase("a")||Languagess1.trim().equalsIgnoreCase("with")
        			||Languagess1.trim().equalsIgnoreCase("for")||Languagess1.trim().equalsIgnoreCase("using")||Languagess1.trim().equalsIgnoreCase("use")||Languagess1.trim().equalsIgnoreCase("used")||Languagess1.trim().equalsIgnoreCase("via")) 
        	{
        		
        	}
        	
        	else if (!Languagess1.trim().isEmpty() && question.contains(Languagess1.trim())) {
                return Languages;
            }
        }
        
        
        
        return "No";
	

    
    }
    
    
    
    
    
 //draw gradient text
    class GradientTextPanel extends JPanel {
        private String text;
        private Font font;

        public GradientTextPanel(String text, Font font) {
            this.text = text;
            this.font = font;
            setOpaque(false); 
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            GradientPaint gradient = new GradientPaint(
                0, 0, Color.decode("#FFFFFF"),  
                getWidth(), 0, Color.decode("#FFFFFF") 
            );
            g2d.setPaint(gradient);
            g2d.setFont(font);

            FontMetrics metrics = g2d.getFontMetrics(font);
            int x = (getWidth() - metrics.stringWidth(text)) / 2; 
            int y = (getHeight() + metrics.getAscent()) / 2 - metrics.getDescent(); 
            g2d.drawString(text, x, y);
        }
    }


    /**
     * @wbp.parser.entryPoint
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LinkedIn::new);
    }
}
