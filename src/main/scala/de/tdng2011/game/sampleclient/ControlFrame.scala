package de.tdng2011.game.sampleclient

import swing.event.ButtonClicked
import swing.{Reactions, ToggleButton, FlowPanel, MainFrame}

class ControlFrame(val client : Client) extends MainFrame {
  setTitlePublicId(client)

  val leftButton : ToggleButton = new ToggleButton("turnLeft") {
    reactions += clickReaction
  }
  val rightButton : ToggleButton = new ToggleButton("turnRight") {
    reactions += clickReaction
  }
  val thrustButton : ToggleButton = new ToggleButton("thrust") {
    reactions += clickReaction
  }
  val fireButton : ToggleButton = new ToggleButton("fire") {
    reactions += clickReaction
  }

  val connectButton : ToggleButton = new ToggleButton("connect") {
    reactions += {
      case x : ButtonClicked => {
        if (connectButton.selected) {
          client.connect
          setTitlePublicId(client)
        } else {
          client.disconnect
          setTitleDisconnected()
        }
      }
    }
    selected = true
  }

  contents = new FlowPanel(leftButton, rightButton, thrustButton, fireButton, connectButton)

  peer.setLocationRelativeTo(null)
  visible = true

  def clickReaction() : Reactions.Reaction = {case x:ButtonClicked => sendAction}
  def sendAction = client !! PlayerActionMessage (leftButton.selected, rightButton.selected, thrustButton.selected, fireButton.selected)

  def setTitlePublicId(client : Client) {title = "Client ID: " + client.getPublicId}
  def setTitleDisconnected() {title = "Client ID: DISCONNECTED"}
}