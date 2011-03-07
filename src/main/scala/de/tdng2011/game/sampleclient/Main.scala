package de.tdng2011.game.sampleclient

import swing.event.ButtonClicked
import swing._

object Main extends SwingApplication{
  def startup(args: Array[String]) {
    val dialog = new MainFrame {
      title = "Connect"
      val hostnameField = new TextField {
        text = "localhost"
        columns = 20
        selectAll
      }
      val nameField = new TextField {
        text = "defaultName"
        columns = 20
        selectAll
      }
      val connectionButton = new Button("Connect!") {
        reactions += {
          case x:ButtonClicked => {
            new ControlFrame(new Client(hostnameField.text, nameField.text))
            close
          }
        }
      }
      defaultButton = connectionButton
      contents = new FlowPanel(hostnameField, nameField, connectionButton)
      peer.setLocationRelativeTo(null)
      visible = true
    }
  }
}