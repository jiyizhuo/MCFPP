package top.mcfpp.model.function

import top.mcfpp.Project
import top.mcfpp.model.CanSelectMember
import top.mcfpp.`var`.lang.Var
import java.lang.UnsupportedOperationException

class UnresolvedNativeFunction(identifier: String, namespace: String = Project.currNamespace) : Function(identifier, namespace, context = null) {

    var data: String? = null

    val readOnlyParams = ArrayList<FunctionParam>()

    override fun invoke(/*readOnlyArgs: ArrayList<Var<*>>, */normalArgs: ArrayList<Var<*>>, caller: CanSelectMember?) {
        throw UnsupportedOperationException()
    }
}