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
import dss.{Fact, Rule}
import detective.Logic._
import detective.Data
import dss.LogicParseException
import scala.swing.event.ListSelectionChanged


object BestDetectiveFrame extends SimpleSwingApplication {

  lazy val detectivePickerPanel = new GridBagPanel(){
    val c = new Constraints

    val requirementsTextArea = new TextArea(){
      border = Swing.LineBorder(Color.BLACK)
      text = ""
    }
    c.fill = Fill.Both
    c.weightx = 10
    c.weighty = 0.5
    c.gridx = 0
    c.gridy = 0
    c.gridheight = 2
    layout(requirementsTextArea) = c

    val findDetectivesButton = new Button(){
      action = new Action("Find detective"){
        def apply():Unit = resultsTextArea.text = findDetective(requirementsTextArea.text).toString
      }
    }
    c.weightx = 1
    c.weighty = 0
    c.gridx = 1
    c.gridy = 0
    c.gridheight = 1
    layout(findDetectivesButton) = c

    val resultsTextArea = new TextArea(){
      border = Swing.LineBorder(Color.BLACK)
      text = ""
    }
    c.weightx = 1
    c.weighty = 1
    c.gridx = 1
    c.gridy = 1
    layout(resultsTextArea) = c

  }
  lazy val ruleManagerPanel = new GridBagPanel(){
    val c = new Constraints

    val rulesList = new ListView[Rule](){
      selection.intervalMode = ListView.IntervalMode.Single
      listData = Data.rules
    }
    c.fill = Fill.Both
    c.weightx = 1
    c.weighty = 20
    c.gridx = 0
    c.gridy = 0
    c.gridwidth = 2
    layout(rulesList) = c

    def selectedRule = rulesList.listData(rulesList.selection.leadIndex)

    listenTo(rulesList.selection)
    reactions += {
      case ListSelectionChanged(event) => removeRuleTextField.text = selectedRule.toString
    }

    val addRuleTextField = new TextField(){}
    c.weightx = 9
    c.weighty = 1
    c.gridx = 0
    c.gridy = 1
    c.gridwidth = 1
    layout(addRuleTextField) = c

    val addRuleButton = new Button(){
      action = new Action("Add rule") {
        def apply(): Unit = try{
          addRule(addRuleTextField.text)
          rulesList.listData = Data.rules
          addRuleTextField.text = ""
        } catch {
          case LogicParseException(exceptionMessage) => Dialog.showMessage(message=exceptionMessage,title = "Rule parsing failed")
        }
      }
    }
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

    val removeRuleButton = new Button(){
      action = new Action("Remove rule"){
        def apply():Unit = {
          if(removeRuleTextField.text.isEmpty) Dialog.showMessage(message = "No rule selected",title = "Error")
          else {
            removeRule(selectedRule)
            rulesList.listData = Data.rules
            removeRuleTextField.text = ""
          }
        }
      }
    }
    c.weightx = 1
    c.weighty = 1
    c.gridx = 1
    c.gridy = 2
    layout(removeRuleButton) = c

  }
  lazy val factManagerPanel = new GridBagPanel(){
    val c = new Constraints

    val factsList = new ListView[Fact](){
      selection.intervalMode = ListView.IntervalMode.Single
      listData = Data.facts
    }
    c.fill = Fill.Both
    c.weightx = 1
    c.weighty = 20
    c.gridx = 0
    c.gridy = 0
    c.gridwidth = 2
    layout(factsList) = c

    def selectedFact = factsList.listData(factsList.selection.leadIndex)

    listenTo(factsList.selection)
    reactions += {
      case ListSelectionChanged(event) => removeFactTextField.text = selectedFact.toString
    }

    val addFactTextField = new TextField(){}
    c.weightx = 9
    c.weighty = 1
    c.gridx = 0
    c.gridy = 1
    c.gridwidth = 1
    layout(addFactTextField) = c

    val addFactButton = new Button(){
      action = new Action("Add fact") {
        def apply(): Unit = try{
          addFact(addFactTextField.text)
          factsList.listData = Data.facts
          addFactTextField.text = ""
        } catch {
          case LogicParseException(exceptionMessage) => Dialog.showMessage(message=exceptionMessage,title = "Fact parsing failed")
        }
      }
    }
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

    val removeFactButton = new Button(){
      action = new Action("Remove fact"){
        def apply():Unit = {
          if(removeFactTextField.text.isEmpty) Dialog.showMessage(message = "No fact selected",title = "Error")
          else {
            removeFact(selectedFact)
            factsList.listData = Data.facts
            removeFactTextField.text = ""
          }
        }
      }
    }
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