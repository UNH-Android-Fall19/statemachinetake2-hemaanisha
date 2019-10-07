package com.example.statemachinetake2.actions

import com.example.statemachinetake2.model.Sub
import com.example.statemachinetake2.model.SubType

sealed class Actions {
    class AddSubClicked: Actions()
    class SubTypeSelected(val type: SubType): Actions()
    class PredefinedSubSelected(val sub: Sub): Actions()
    class SubmitSubClicked(val subName: String): Actions()
}