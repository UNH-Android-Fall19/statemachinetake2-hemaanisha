package com.example.statemachinetake2.states

import com.example.statemachinetake2.actions.Actions
import com.example.statemachinetake2.model.Sub

class SubList(val sub: List<Sub>): SubState {
    override fun consumeActions(action: Actions): SubState {
        return when(action) {
            is Actions.AddSubClicked -> AddSub(sub)
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}