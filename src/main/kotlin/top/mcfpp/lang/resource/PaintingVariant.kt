
package top.mcfpp.lang.resource
            
import top.mcfpp.command.Command
import top.mcfpp.command.Commands
import top.mcfpp.lang.Var
import top.mcfpp.lang.type.MCFPPResourceType
import top.mcfpp.lang.type.MCFPPType
import top.mcfpp.lang.value.MCFPPValue
import top.mcfpp.model.CompoundData
import top.mcfpp.model.FieldContainer
import java.util.*
import top.mcfpp.model.function.Function
import top.mcfpp.mni.resource.PaintingVariantData
import top.mcfpp.mni.resource.PaintingVariantConcreteData

open class PaintingVariant: ResourceID {

    override var type: MCFPPType = MCFPPResourceType.PaintingVariant

    /**
     * 创建一个PaintingVariant类型的变量。它的mc名和变量所在的域容器有关。
     *
     * @param identifier 标识符。默认为
     */
    constructor(
        curr: FieldContainer,
        identifier: String = UUID.randomUUID().toString()
    ) : super(curr.prefix + identifier) {
        this.identifier = identifier
    }

    /**
     * 创建一个PaintingVariant值。它的标识符和mc名相同。
     * @param identifier identifier
     */
    constructor(identifier: String = UUID.randomUUID().toString()) : super(identifier){
        isTemp = true
    }

    /**
     * 复制一个PaintingVariant
     * @param b 被复制的PaintingVariant值
     */
    constructor(b: PaintingVariant) : super(b)

    override fun doAssign(b: Var<*>): PaintingVariant {
        return super.assign(b) as PaintingVariant
    }

    companion object {
        val data = CompoundData("PaintingVariant","mcfpp.lang.resource")

        init {
            data.initialize()
            data.extends(ResourceID.data)
            data.getNativeFunctionFromClass(PaintingVariantData::class.java)
        }
    }
}

class PaintingVariantConcrete: MCFPPValue<String>, PaintingVariant{

    override var value: String

    constructor(
        curr: FieldContainer,
        value: String,
        identifier: String = UUID.randomUUID().toString()
    ) : super(curr.prefix + identifier) {
        this.value = value
    }

    constructor(value: String, identifier: String = UUID.randomUUID().toString()) : super(identifier) {
        this.value = value
    }

    constructor(id: PaintingVariant, value: String) : super(id){
        this.value = value
    }

    constructor(id: PaintingVariantConcrete) : super(id){
        this.value = id.value
    }

    override fun clone(): PaintingVariantConcrete {
        return PaintingVariantConcrete(this)
    }

    override fun getTempVar(): PaintingVariantConcrete {
        return PaintingVariantConcrete(this.value)
    }

    override fun toDynamic(replace: Boolean): Var<*> {
        val parent = parent
        if (parentClass() != null) {
            val cmd = Commands.selectRun(parent!!, "data modify entity @s data.${identifier} set value $value")
            Function.addCommands(cmd)
        } else {
            val cmd = Command.build("data modify")
                .build(nbtPath.toCommandPart())
                .build("set value $value")
            Function.addCommand(cmd)
        }
        val re = PaintingVariant(this)
        if(replace){
            Function.currFunction.field.putVar(identifier, re, true)
        }
        return re
    }

    override fun toString(): String {
        return "[$type,value=$value]"
    }
    
    companion object {
        val data = CompoundData("PaintingVariant","mcfpp.lang.resource")

        init {
            data.initialize()
            data.extends(ResourceID.data)
            data.getNativeFunctionFromClass(PaintingVariantConcreteData::class.java)
        }
    }
    
}        
