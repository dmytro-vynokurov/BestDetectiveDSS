package view

/**
* User: Dmytro Vynokurov
* Date: 12/14/13
* Time: 3:27 PM
*/

import scala.swing._
import scala.swing.TabbedPane.Page
import scala.swing.GridBagPanel.Fill
import java.awt.Color
import src.dss.{Fact, Rule}


object BestDetectiveFrame extends SimpleSwingApplication {

  lazy val detectivePickerPanel = new GridBagPanel(){
    val c = new Constraints

    val requirementsTextArea = new TextArea(){
      border = Swing.LineBorder(Color.BLACK)
      text = "Text area 1"
    }
    c.fill = Fill.Both
    c.weightx = 10
    c.weighty = 0.5
    c.gridx = 0
    c.gridy = 0
    c.gridheight = 2
    layout(requirementsTextArea) = c

    val findDetectivesButton = new Button("Find detective"){}
    c.weightx = 1
    c.weighty = 0
    c.gridx = 1
    c.gridy = 0
    c.gridheight = 1
    layout(findDetectivesButton) = c

    val resultsTextArea = new TextArea(){
      border = Swing.LineBorder(Color.BLACK)
      text = "TA 2"
    }
    c.weightx = 1
    c.weighty = 1
    c.gridx = 1
    c.gridy = 1
    layout(resultsTextArea) = c

  }
  lazy val ruleManagerPanel = new GridBagPanel(){
    val c = new Constraints

    val rulesList = new ListView[Rule](){}
    c.fill = Fill.Both
    c.weightx = 1
    c.weighty = 20
    c.gridx = 0
    c.gridy = 0
    c.gridwidth = 2
    layout(rulesList) = c

    val addRuleTextField = new TextField(){}
    c.weightx = 9
    c.weighty = 1
    c.gridx = 0
    c.gridy = 1
    c.gridwidth = 1
    layout(addRuleTextField) = c

    val addRuleButton = new Button("Add rule"){}
    c.weightx = 1
    c.weighty = 1
    c.gridx = 1
    c.gridy = 1
    layout(addRuleButton) = c

    val removeRuleTextField = new TextField(){}
    c.weightx = 9
    c.weighty = 1
    c.gridx = 0
    c.gridy = 2
    layout(removeRuleTextField) = c

    val removeRuleButton = new Button("Remove rule"){}
    c.weightx = 1
    c.weighty = 1
    c.gridx = 1
    c.gridy = 2
    layout(removeRuleButton) = c

  }
  lazy val factManagerPanel = new GridBagPanel(){
    val c = new Constraints

    val factsList = new ListView[Fact](){}
    c.fill = Fill.Both
    c.weightx = 1
    c.weighty = 20
    c.gridx = 0
    c.gridy = 0
    c.gridwidth = 2
    layout(factsList) = c

    val addFactTextField = new TextField(){}
    c.weightx = 9
    c.weighty = 1
    c.gridx = 0
    c.gridy = 1
    c.gridwidth = 1
    layout(addFactTextField) = c

    val addFactButton = new Button("Add fact"){}
    c.weightx = 1
    c.weighty = 1
    c.gridx = 1
    c.gridy = 1
    layout(addFactButton) = c

    val removeFactTextField = new TextField(){}
    c.weightx = 9
    c.weighty = 1
    c.gridx = 0
    c.gridy = 2
    layout(removeFactTextField) = c

    val removeFactButton = new Button("Remove fact"){}
    c.weightx = 1
    c.weighty = 1
    c.gridx = 1
    c.gridy = 2
    layout(removeFactButton) = c

  }

  def top = new MainFrame {
    title = "ScalaSwingExample"
    preferredSize = new Dimension(700,400)
    contents = new TabbedPane(){
      pages += new Page("Detective picker",detectivePickerPanel)
      pages += new Page("Rule manager",ruleManagerPanel)
      pages += new Page("Fact manager",factManagerPanel)
    }
  }

}