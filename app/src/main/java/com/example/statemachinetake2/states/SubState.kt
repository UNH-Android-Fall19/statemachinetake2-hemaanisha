package com.example.statemachinetake2.states

import com.example.statemachinetake2.actions.Actions

interface  SubState {
    fun consumeActions(action: Actions): SubState
}