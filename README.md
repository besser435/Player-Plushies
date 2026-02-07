
# Player Plushies Mod

## TODO (in order):
* Bugs (urgent)
  * Fix outer skin layer. Should it be closer to the model?
  * Support Steve model. Alex works fine, but Steve has holes. Verify this because I only tested one skin.
  * Fix different pixel size. The head which is 1:1 scale, everything else which is smaller, and then the side 
  of the torso is two pixels wide.
  * Fix rotation states. We want 16 different rotation states like a sign or player head. To use more model states other than 
  90 degree increments, we need either a block entity renderer, or use different models that have baked in rotation. Since we
  want to avoid being a block entity, we will probably need to bake different models. <br>Revisit this later. If we 
  decide to use a BE after all, use a BER. Otherwise, bake different models. <br>BER would also allow for lowering the
  model into a bed without a duplicate model, using just code.

* Features
  * Water loggable 
  * Default skin/texture
  * 🏳️‍⚧️ fluffy shark plushie (blahaj)

* Stretch features
  * Custom holding animation like how some blahaj mods use the crossbow position
  * Support older versions of NeoForge
  * Dinnerbone should be upside down, and jeb_ should maybe be rainbow or spinny
