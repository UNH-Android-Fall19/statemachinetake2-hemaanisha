package com.example.statemachinetake2.states

import com.example.statemachinetake2.actions.Actions
import com.example.statemachinetake2.model.Sub
import com.example.statemachinetake2.model.SubType

class NameSub(private val sub: List<Sub>, private val newSubType: SubType): SubState {
    override fun consumeActions(action: Actions): SubState {
        return when (action) {
            is Actions.SubmitSubClicked -> {
                val newSub = Sub(action.subName, newSubType)
                SubList(sub + newSub)
            }
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}