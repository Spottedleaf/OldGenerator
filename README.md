OldGenerator
==
Spigot plugin for adding Beta 1.7.3 generation to modern versions of
Minecraft. This plugin can replace 
[173generator](https://github.com/Barteks2x/173generator) for modern versions (please
see the How to Use section for proper configuration).


## Showcase
### Seed `worstseedever`

### Beta 1.7.3
![Overworld Generation](https://i.imgur.com/AS4aeoK.png)
![Nether Generation](https://i.imgur.com/7lOgLRC.png)

### OldGenerator on Minecraft 1.16.4
![Overworld Generation](https://i.imgur.com/0ycd5K0.png)
![Nether Generation](https://i.imgur.com/u5OTgPZ.png)

### Differences
#### Overworld
Block physics are not applied to gravel OR sand when placing. So, the dungeon sand will
not immediately cave in upon logging in, but the dungeon is still there .
Most chunks will look the exact same, but due to chunk generation order differences
the populators might fire differently and you'll get different results (however the general
terrain will remain the same).

#### Nether
Fire, glowstone, and even lava placement (excluding the lava oceans) will generally not mirror Beta 1.7.3. However, the terrain
will remain the same.

Why aren't fire/glowstone/lava correct? This is due to a technical bug in the old
beta populator code: The chunk seed is not setup when populating chunks! So the 
placement is entirely dependent on chunk generation order (and a few other really technical details), 
which is completely different in modern Minecraft. 


There's nothing I can do to match the old populator behavior, however I have modified the 
populator code for nether to correctly setup a chunk seed per chunk - this means the results 
should be consistent across world generations when using OldGenerator, but they still will not 
match Vanilla Beta 1.7.3.


## How do I use?
#### If you do NOT have a world management plugin
Add this to your bukkit.yml
```yaml
worlds:
  worldname1:
    generator: OldGenerator:b173
  worldname2:
    generator: OldGenerator:b173
```
This will setup worldname1 and worldname2 to run OldGenerator as their generator. For example,
you can set worldname1 to `world` and worldname2 to `world_nether` to do exactly what you expect.
`b173` can be replaced with `sb173` to get a [skylands](https://minecraft.gamepedia.com/Sky_dimension) world.

#### If you have a world management plugin
Follow the guide that world management plugin to setup custom generators. I don't
use any world management plugins, so I can't help you!

## I want different old generators!
If enough people request other old generators, I can add them to this plugin. 

## License
All of the world generation code is directly copied from decompiled sources 
of the minecraft server jar (specifically, the beta 1.7.3 dedicated server jar), 
and then modified further. The original decompiled sources can be found here: 
https://github.com/Bukkit/mc-dev

As such, the world generation code is copyrighted by Mojang AB.

Everything else is licensed under the MIT License, see the LICENSE.md file for license text.