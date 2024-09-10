package expo.modules.view

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ExpoViewModule : Module() {

  override fun definition() = ModuleDefinition {
    Name("ExpoView")

    View(ExpoView::class) {
      Prop("name") { view: ExpoView, prop: String ->
        println(prop)
      }
    }
  }
}
