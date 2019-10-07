package com.example.statemachinetake2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.statemachinetake2.R
import com.example.statemachinetake2.actions.Actions
import com.example.statemachinetake2.model.Sub
import com.example.statemachinetake2.model.SubType
import com.example.statemachinetake2.states.AddSub
import com.example.statemachinetake2.states.NameSub
import com.example.statemachinetake2.states.SubList
import com.example.statemachinetake2.states.SubState
import kotlinx.android.synthetic.main.activity_sub.*
import org.intellij.lang.annotations.Subst
import kotlin.properties.Delegates

class SubActivity : AppCompatActivity() {

    private val predefinedSubs: MutableList<Sub> = mutableListOf()

    var currentState by Delegates.observable<SubState>(
        SubList(
            emptyList()
        ), { _, old, new ->
            renderViewState(new, old)
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildPredefinedSubs()
        setContentView(R.layout.activity_sub)
        showSubList(emptyList())
    }

    private fun buildPredefinedSubs() {
        predefinedSubs.add(Sub("Veggie", SubType.SUB))
        predefinedSubs.add(Sub("Savory Rotisserie-Style Chicken Caesar", SubType.WRAP))
        predefinedSubs.add(Sub("Black Forest Ham", SubType.SUB))
        predefinedSubs.add(Sub("Meatball Marinara", SubType.WRAP))
        predefinedSubs.add(Sub("Steak & Cheese", SubType.SUB))
        predefinedSubs.add(Sub("Sweet Onion Chicken Teriyaki", SubType.WRAP))
        predefinedSubs.add(Sub("Turkey Breast", SubType.WRAP))
    }

    private fun renderViewState(newState: SubState, oldState: SubState) {
        when (newState) {
            is SubList -> showSubList(newState.sub)
            is AddSub -> showAddSubView(predefinedSubs)
            is NameSub -> showSubInputFields()
        }

        when (oldState) {
            is SubList -> hideSubList()
            is AddSub -> hideAddSubView()
            is NameSub -> hideSubInputFields()
        }
    }


    private fun showSubList(subs: List<Sub>) {
        sub_list_container.visibility = View.VISIBLE
        favorite_subs_listview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, subs)
        add_sub_button.setOnClickListener {
            currentState = currentState.consumeActions(Actions.AddSubClicked())
        }
    }

    private fun hideSubList() {
        sub_list_container.visibility = View.GONE
    }

    private fun showAddSubView(predefinedSub: List<Sub>) {
        add_sub_container.visibility = View.VISIBLE
        wrap_button.setOnClickListener {
            currentState = currentState.consumeActions(Actions.SubTypeSelected(SubType.WRAP))
        }

        sub_button.setOnClickListener {
            currentState = currentState.consumeActions(Actions.SubTypeSelected(SubType.SUB))
        }
        predefined_subs_listview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, predefinedSub)
        predefined_subs_listview.setOnItemClickListener { _, _, position, _ ->
            val selectedSub = predefinedSub[position]
            currentState = currentState.consumeActions(Actions.PredefinedSubSelected(selectedSub))
        }
    }

    private fun hideAddSubView() {
        add_sub_container.visibility = View.GONE
    }

    private fun showSubInputFields() {
        sub_inputs_container.visibility = View.VISIBLE
        submit_button.setOnClickListener {
            val subName = sub_name.text.toString()
            sub_name.text.clear()
            currentState = currentState.consumeActions(Actions.SubmitSubClicked(subName))
        }
    }

    private fun hideSubInputFields() {
        sub_inputs_container.visibility = View.GONE
    }

}
