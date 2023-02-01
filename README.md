# Ceresbot

A helpful Discord bot for looking up D&D-related data.

**Setup**

1. Ensure you have Java and Gradle installed.
2. `git clone` the project.
3. Create an `.env` file in the project root directory, and set an enviroment variable called `DISCORD_API_KEY` equal to your Discord bot API key. 
4. Add a spells.json project file at `src/main/resources/spells.json` with the [following schema](https://cdn.discordapp.com/attachments/363199559054393357/1067256900409893015/image.png) (`type` and `tags` currently unused).
5. In your terminal, run the `gradle run` command.

**Current commands**
      
`!roll <number of dice>d<dice>` - Simulates a dice roll for the specified dice values.    
`!spell <spell>` - Provides information on the given 5e D&D spell.  
`!spellscroll <spell>` - Calculates the price of a spell scroll for the given 5e D&D spell.
