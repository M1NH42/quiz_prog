import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
class  Quiz extends JFrame implements ActionListener{
            JPanel panel;
            JPanel panelresult;
            JRadioButton choice1;
            JRadioButton choice2;
            JRadioButton choice3;
            JRadioButton choice4;
            ButtonGroup bg;
            JLabel lblmess;
            JButton btnext;
            String[][] qpa;
            String[][] qca;
            int qaid;
            HashMap<Integer, String> map;

            Quiz(){
initializedata();
                      setTitle("Quiz Program");
                      setDefaultCloseOperation(EXIT_ON_CLOSE);
                      setSize(700,450);
                      setLocation(300,100);
                      setResizable(false);
                      Container cont=getContentPane();
                      cont.setLayout(null);
                     cont.setBackground(Color.RED);
                    bg=new ButtonGroup();
	    choice1=new JRadioButton("Choice1",false);
                    choice1.setBackground(Color.GREEN);
	    choice2=new JRadioButton("Choice2",false);
 	    choice2.setBackground(Color.GREEN);
                    choice3=new JRadioButton("Choice3",false);
 	    choice3.setBackground(Color.GREEN);
                    choice4=new JRadioButton("Choice4",false);
                    choice4.setBackground(Color.GREEN);
	    bg.add(choice1);
                    bg.add(choice2);
                    bg.add(choice3);
                    bg.add(choice4);
                    lblmess=new JLabel("Choose a correct anwswer");
                   lblmess.setForeground(Color.BLUE);
                    lblmess.setFont(new Font("Arial", Font.BOLD, 11));
                    btnext=new JButton("Next");
                    btnext.setForeground(Color.GREEN);
                    btnext.addActionListener(this);
                    panel=new JPanel();
                    panel.setBackground(Color.YELLOW);
                    panel.setLocation(10,10);
                    panel.setSize(700,450);
                    panel.setLayout(new GridLayout(6,2));
                    panel.add(lblmess);
                    panel.add(choice1);
                    panel.add(choice2);
                    panel.add(choice3);
                    panel.add(choice4);
                    panel.add(btnext);
                    cont.add(panel);
                    setVisible(true);
                    qaid=0;
                    readqa(qaid);


            }
public void actionPerformed(ActionEvent e){

                        if(btnext.getText().equals("Next")){
                                    if(qaid<4){

                                                map.put(qaid,getSelection());
                                                qaid++;
                                                readqa(qaid);
                                                }
                                    else {
                                                map.put(qaid,getSelection());
                                                btnext.setText("Show answers");

                                             }
                                    }
                        else if(btnext.getText().equals("Show answers"))
                                    new Report();


            }
  public void initializedata(){
                        //qpa stores pairs of question and its possible answers
                        qpa=new String[5][5];

                        qpa[0][0]="How to run Java program on the command prompt?";
                        qpa[0][1]="javac JavaProgram";
                        qpa[0][2]="java JavaProgram";
                        qpa[0][3]="javac JavaProgram.java";
                        qpa[0][4]="No one";

                        qpa[1][0]="What is the use of the println method?";
                        qpa[1][1]="It is used to print text on the screen.";
                        qpa[1][2]="It is used to print text on the screen with the line break.";
                        qpa[1][3]="It is used to read text from keyboard.";
                        qpa[1][4]="It is used to read text from a file.";

                        qpa[2][0]="How to read a character from the keyboard?";
                        qpa[2][1]="char c=System.read()";
                        qpa[2][2]="char c=System.in.read()";
                        qpa[2][3]="char c=(char)System.read()";
                        qpa[2][4]="char c=(char)System.in.read()";

                        qpa[3][0]="Which one is a single-line comment?";
                        qpa[3][1]="/...";
                        qpa[3][2]="//...";
                        qpa[3][3]="/*...";
                        qpa[3][4]="/*...*/";

                        qpa[4][0]="How do you declare an integer variable x?";
                        qpa[4][1]="int x";
                        qpa[4][2]="x as Integer";
                        qpa[4][3]="Int[] x";
                        qpa[4][4]="No one is correct.";

              //qca stores pairs of question and its correct answer
                        qca=new String[5][2];

                        qca[0][0]="How to run Java program on the command prompt?";
                        qca[0][1]="java JavaProgram";

                        qca[1][0]="What is the use of the println method?";
                        qca[1][1]="It is used to print text on the screen with the line break.";

                        qca[2][0]="How to read a character from the keyboard?";
                        qca[2][1]="char c=(char)System.in.read()";

                        qca[3][0]="Which one is a single-line comment?";
                        qca[3][1]="//...";


                        qca[4][0]="How do you declare an integer variable x?";
                        qca[4][1]="int x";

   //create a map object to store pairs of question and selected answer
                        map=new HashMap<Integer, String>();

                        }
 public String getSelection(){
                        String selectedChoice=null;
                        Enumeration<AbstractButton> buttons=bg.getElements();
                        while(buttons.hasMoreElements())
                        {
                        JRadioButton temp=(JRadioButton)buttons.nextElement();
                        if(temp.isSelected())
                                    {
                                                selectedChoice=temp.getText();
                                    }
                         }
                        return(selectedChoice);
            }

 public void readqa(int qid){
                        lblmess.setText("  "+qpa[qid][0]);
                        choice1.setText(qpa[qid][1]);
                        choice2.setText(qpa[qid][2]);
                        choice3.setText(qpa[qid][3]);
                        choice4.setText(qpa[qid][4]);
                      }
 public void reset(){
                        qaid=0;
                        map.clear();
                        readqa(qaid);
                        btnext.setText("Next");
                        }

 public int calCorrectAnswer(){
                        int qnum=5;
                        int count=0;
                        for(int qid=0;qid<qnum;qid++)
                                    if(qca[qid][1].equals(map.get(qid))) count++;
                        return count;
            }

public class Report extends JFrame{
                        Report(){
                                    setTitle("Answers");
                                    setSize(850,550);
                                    setBackground(Color.WHITE);
                                    addWindowListener(new WindowAdapter(){
                                                            public void windowClosing(WindowEvent e){
                                                                        dispose();
                                                                        reset();
                                                            }
                                                });
                                    Draw d=new Draw();
                                    add(d);
                                    setVisible(true);
                                    }

 class Draw extends Canvas{
                                    public void paint(Graphics g){
                                                int qnum=7;
                                                int x=10;
                                                int y=20;
                                                for(int i=0;i<qnum;i++){
                                                            //print the 1st column
                                                            g.setFont(new Font("Arial",Font.BOLD,12));
                                                            g.drawString(i+1+". "+qca[i][0], x,y);
                                                            y+=30;
                                                            g.setFont(new Font("Arial",Font.PLAIN,12));
                                                            g.drawString("      Correct answer:"+qca[i][1], x,y);
                                                            y+=30;
                                                            g.drawString("      Your answer:"+map.get(i), x,y);
                                                            y+=30;
                                                            //print the 2nd column
                                                            if(y>400)
                                                            {y=20;
                                                              x=450;
                                                            }

                                                }
                                                //Show number of correct answers
                                                int numc=calCorrectAnswer();
                                                g.setColor(Color.BLUE);
                                                g.setFont(new Font("Arial",Font.BOLD,14));
                                                g.drawString("Number of correct answers:"+numc,300,500);


                                    }
                        }

            }




}

public class QuizProgram{

            public static void main(String args[]){
                         new Quiz();

            }


}
  //          }
