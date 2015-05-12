import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class Application extends JFrame implements ActionListener{
	public static enum CurrentClass{
		ALGEBRA1, GEOMETRYPRINCIPLES, GEOMETRY, GEOMETRYHONORS, INTERMEDIATEALGEBRA,ALGEBRA2,
		ALGEBRA2HONORS, PRECALCULUS, PRECALCULUSHONORS, APSTATISTICS, NONAPCALCULUS, APCALCULUSAB, APCALCULUSBC
	}
	
	public static CurrentClass currentClass;
	
	public static HashMap<String, CurrentClass> currentClassHolder = new HashMap<String, CurrentClass>();
	
	JPanel instructionsPanel = new JPanel(), gradesPanel = new JPanel(),submitPanel = new JPanel(),
			recommendPanel = new JPanel(), middlePanel = new JPanel();
	//Instruction Panel
	//JLabel instructions = new JLabel("These are the instructions");
	JTextArea instructionsText = new JTextArea();
	
	
	//Grades/Class Panel
	JLabel currentClassLabel = new JLabel("Current Class:"), semester1Label = new JLabel("Semester 1 Grade:"), 
	semester2Label = new JLabel("Semester 2 Grade:");
	Choice currentClassChoice = new Choice();
	Choice semester1Choice = new Choice(), semester2Choice = new Choice();
	
	//Submit/NCAA/Future Plan Panel
	JLabel futurePlanLabel = new JLabel("Future Plan:"), NCAALabel = new JLabel("NCAA Recognized Class:"), spaceTaker = new JLabel("");
	JButton submit = new JButton("Submit");
	Choice futurePlanChoice = new Choice(), NCAAChoice = new Choice();
	
	//Recommend Class Panel
	JLabel recommendLabel = new JLabel("Recommended Class:");
	JTextField recommendField = new JTextField(20);
	
	String[] Classes = {"Algebra 1","Geometry Principles","Geometry","Geometry Honors","Intermediate Algebra",
			"Algebra 2","Algebra 2 Honors","Pre-Calculus","Pre-Calculus Honors","AP Statistics","Non-AP Calculus",
			"AP Calculus AB","AP Calculus BC"};
	
	String[] NCAAOptions = {"Yes","No"};
	
	String[] FuturePlans = {"College (STEM)","College (Liberal Arts)","College (Business)",
			"College (Psych/Social Service","College (Undecided)","Military","Work","Undecided"};
	String[] Grades = {"A","B","C","D","F"};
	public Application(){
		super("Math Class Recommender");
		this.setBounds(0, 0, 320, 400);
		for(String s : Classes){
			currentClassChoice.add(s);
		}
		for(String s : NCAAOptions){
			NCAAChoice.add(s);
		}
		for(String s : FuturePlans){
			futurePlanChoice.add(s);
		}
		for(String s : Grades){
			semester1Choice.add(s);
			semester2Choice.add(s);
		}
		currentClassHolder.put(Classes[0], CurrentClass.ALGEBRA1);
		currentClassHolder.put(Classes[1], CurrentClass.GEOMETRYPRINCIPLES);
		currentClassHolder.put(Classes[2], CurrentClass.GEOMETRY);
		currentClassHolder.put(Classes[3], CurrentClass.GEOMETRYHONORS);
		currentClassHolder.put(Classes[4], CurrentClass.INTERMEDIATEALGEBRA);
		currentClassHolder.put(Classes[5], CurrentClass.ALGEBRA2);
		currentClassHolder.put(Classes[6], CurrentClass.ALGEBRA2HONORS);
		currentClassHolder.put(Classes[7], CurrentClass.PRECALCULUS);
		currentClassHolder.put(Classes[8], CurrentClass.PRECALCULUSHONORS);
		currentClassHolder.put(Classes[9], CurrentClass.APSTATISTICS);
		currentClassHolder.put(Classes[10], CurrentClass.NONAPCALCULUS);
		currentClassHolder.put(Classes[11], CurrentClass.APCALCULUSAB);
		currentClassHolder.put(Classes[12], CurrentClass.APCALCULUSBC);
		
		this.setLayout(new GridLayout(3,1));
		add(instructionsPanel);
		add(middlePanel);
		middlePanel.setLayout(new GridLayout(2,2));
		middlePanel.add(gradesPanel);
		middlePanel.add(submitPanel);
		add(recommendPanel);
		instructionsPanel.setLayout(new GridLayout(1,1));
		gradesPanel.setLayout(new GridLayout(3,1));
		submitPanel.setLayout(new GridLayout(3,1));
		
		//instructionsPanel.add(instructions);
		instructionsPanel.add(instructionsText);
		instructionsText.setEditable(false);
		instructionsText.append("Instructions:\n");
		instructionsText.append("________________________________________________\n");
		instructionsText.append("Select your current math class and select the grades you\n");
		instructionsText.append("received/expect to receive.\n");
		instructionsText.append("STEM is Science/Technology/Engineering/Math.\n");
		instructionsText.append("NCAA is for students planning on playing college sports.\n");
		instructionsText.append("________________________________________________");
		
		
		gradesPanel.add(currentClassLabel);
		gradesPanel.add(currentClassChoice);
		gradesPanel.add(semester1Label);
		gradesPanel.add(semester1Choice);
		gradesPanel.add(semester2Label);
		gradesPanel.add(semester2Choice);
		
		submitPanel.add(futurePlanLabel);
		submitPanel.add(futurePlanChoice);
		submitPanel.add(NCAALabel);
		submitPanel.add(NCAAChoice);
		
		submitPanel.add(spaceTaker);
		submitPanel.add(submit);
		submit.addActionListener(this);
		
		recommendPanel.add(recommendLabel);
		recommendPanel.add(recommendField);
		recommendField.setEditable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(submit))
			findClass(semester1Choice.getSelectedItem(),semester2Choice.getSelectedItem(),futurePlanChoice.getSelectedItem(),NCAAChoice.getSelectedItem(),getCurrentClass());
	}
	
	public void findClass(String s1grade, String s2grade, String futurePlan, String NCAA, CurrentClass currentClass){
		switch(currentClass){
			case ALGEBRA1:
				if((hasA(s1grade) || hasB(s1grade)) && (hasA(s2grade) || hasB(s2grade))){
					setRecommendClass(Classes[3]);
					break;
				}else{
					if((((hasA(s1grade))||(hasB(s1grade)) || (hasC(s1grade)))&&((hasA(s2grade))||(hasB(s2grade)) || (hasC(s2grade))))){
						setRecommendClass(Classes[2]);
						break;
					}else{
						setRecommendClass(Classes[1]);
						break;
					}
				}
			case GEOMETRYPRINCIPLES:
				if((hasA(s1grade)||hasB(s1grade))&&(hasA(s2grade)||hasB(s2grade))){
					setRecommendClass(Classes[5]);
					break;
				}else{
					setRecommendClass(Classes[4]);
					break;
				}
			case GEOMETRY:
				if((hasA(s1grade)||hasB(s1grade))&&(hasA(s2grade)||hasB(s2grade))){
					setRecommendClass(Classes[6]);
					break;
				}else{
					if((((hasA(s1grade))||(hasB(s1grade)) || (hasC(s1grade)))&&((hasA(s2grade))||(hasB(s2grade)) || (hasC(s2grade))))){
						setRecommendClass(Classes[5]);
						break;
					}else{
						setRecommendClass(Classes[4]);
						break;
					}
				}
			case GEOMETRYHONORS:
				if((hasA(s1grade)||hasB(s1grade))&&(hasA(s2grade)||hasB(s2grade))){
					setRecommendClass(Classes[6]);
					break;
				}else{
					if((((hasA(s1grade))||(hasB(s1grade)) || (hasC(s1grade)))&&((hasA(s2grade))||(hasB(s2grade)) || (hasC(s2grade))))){
						setRecommendClass(Classes[5]);
						break;
					}else{
						setRecommendClass(Classes[4]);
						break;
					}
				}
			case INTERMEDIATEALGEBRA:
				setRecommendClass(Classes[5]);
				break;
			case ALGEBRA2:
				if((hasA(s1grade)||hasB(s1grade))&&(hasA(s2grade)||hasB(s2grade))){
					if(futurePlan.equals(FuturePlans[0])){
						setRecommendClass(Classes[8]);
						break;
					}else{
						setRecommendClass(Classes[9]);
						break;
					}
				}else{
					if((((hasA(s1grade))||(hasB(s1grade)) || (hasC(s1grade)))&&((hasA(s2grade))||(hasB(s2grade)) || (hasC(s2grade))))){
						if(futurePlan.equals(FuturePlans[0])){
							setRecommendClass(Classes[7]);
							break;
						}else{
							setRecommendClass(Classes[7]);
							break;
						}
					}else{
						setRecommendClass(Classes[7]);
						break;
					}
				}
			case ALGEBRA2HONORS:
				if((((hasA(s1grade))||(hasB(s1grade)) || (hasC(s1grade)))&&((hasA(s2grade))||(hasB(s2grade)) || (hasC(s2grade))))){
					setRecommendClass(Classes[8]);
					break;
				}else{
					setRecommendClass(Classes[7]);
					break;
				}
			case PRECALCULUS:
				if((hasA(s1grade)&&hasA(s2grade))&&(futurePlan.equals(FuturePlans[0]))){
					setRecommendClass(Classes[12]);
					break;
				}else{
					if((hasA(s1grade)||hasB(s1grade))&&(hasA(s2grade)||hasB(s2grade))){
						setRecommendClass(Classes[11]);
						break;
					}else{
						if((((hasA(s1grade))||(hasB(s1grade)) || (hasC(s1grade)))&&
								((hasA(s2grade))||(hasB(s2grade)) || (hasC(s2grade))))&&
								!(futurePlan.equals(FuturePlans[0]))){
							setRecommendClass(Classes[9]);
							break;
						}else{
							setRecommendClass(Classes[10]);
							break;
						}
					}
				}
			case PRECALCULUSHONORS:
				if((hasA(s1grade))&&(hasA(s2grade))){
					setRecommendClass(Classes[12]);
					break;
				}else{
					if((hasA(s1grade)||hasB(s1grade))&&(hasA(s2grade)||hasB(s2grade))){
						setRecommendClass(Classes[11]);
						break;
					}else{
						if(((((hasA(s1grade))||(hasB(s1grade)) || (hasC(s1grade)))&&((hasA(s2grade))||(hasB(s2grade)) || (hasC(s2grade)))))&&(futurePlan.equals(FuturePlans[0]))){
							setRecommendClass(Classes[10]);
							break;
						}else{
							setRecommendClass(Classes[9]);
							break;
						}
					}
				}
			case NONAPCALCULUS:
				if((hasA(s1grade))&&(hasA(s2grade))){
					setRecommendClass(Classes[12]);
					break;
				}else{
					if((hasA(s1grade)||hasB(s1grade))&&(hasA(s2grade)||hasB(s2grade))){
						setRecommendClass(Classes[11]);
						break;
					}else{
						setRecommendClass(Classes[9]);
						break;
					}
				}
			case APSTATISTICS:
				if((hasA(s1grade))&&(hasA(s2grade))){
					setRecommendClass(Classes[12]);
					break;
				}else{
					if((hasA(s1grade)||hasB(s1grade))&&(hasA(s2grade)||hasB(s2grade))){
						setRecommendClass(Classes[11]);
						break;
					}else{
						setRecommendClass(Classes[10]);
						break;
					}
				}
			case APCALCULUSAB:
				setRecommendClass(Classes[12]);
				break;
			case APCALCULUSBC:
				setRecommendClass(Classes[9]);
				break;
			default:
				
				break;
		}
	}
	public CurrentClass getCurrentClass(){
		return currentClassHolder.get(currentClassChoice.getSelectedItem());
	}
	public void setRecommendClass(String s){
		recommendField.setText(s);
	}
	public boolean hasA(String grade){
		if(grade.equals(Grades[0])){
			return true;
		}else{
			return false;
		}
	}
	public boolean hasB(String grade){
		if(grade.equals(Grades[1])){
			return true;
		}else{
			return false;
		}
	}
	public boolean hasC(String grade){
		if(grade.equals(Grades[2])){
			return true;
		}else{
			return false;
		}
	}
	public boolean hasD(String grade){
		if(grade.equals(Grades[3])){
			return true;
		}else{
			return false;
		}
	}
	public boolean hasF(String grade){
		if(grade.equals(Grades[4])){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args){
		Application application = new Application();
		application.setVisible(true);
		application.setResizable(false);
		//application.pack();
	}
}