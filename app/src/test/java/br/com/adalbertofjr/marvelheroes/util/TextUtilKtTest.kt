package br.com.adalbertofjr.marvelheroes.util

import android.app.Activity
import br.com.adalbertofjr.marvelheroes.R
import br.com.adalbertofjr.marvelheroes.characters.CharacterViewModel
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TextUtilKtTest {

    @Mock
    private lateinit var activity: Activity


    @Test
    fun should_ReturnDescriptiontText_WhenReceiveACharacterData() {
        //Creating scene
        Mockito.`when`(activity.getString(R.string.message_no_description))
            .thenReturn("\"Macacos me mordam Batman!\\n\\nParece que alguém esqueceu de escrever minha descrição.\"")

        val character = CharacterViewModel(
            "A-Bomb (HAS)",
            "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ",
            "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_fantastic.jpg",
            "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_fantastic.jpg"
        )

        //Executing
        val textDevolvido =
            character.description.textOrUseThis(message = activity.getString(R.string.message_no_description))

        //Testing
        Assert.assertThat(
            textDevolvido,
            CoreMatchers.`is`(CoreMatchers.equalTo<String>("Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! "))
        )
    }

    @Test
    fun should_ReturnDescriptiontTextWithGetStringMessage_WhenReceiveACharacterDataDescriptionEmpty() {
        //Creating scene
        Mockito.`when`(activity.getString(R.string.message_no_description))
            .thenReturn("\"Macacos me mordam Batman!\\n\\nParece que alguém esqueceu de escrever minha descrição.\"")

        val character = CharacterViewModel(
            "A-Bomb (HAS)",
            "",
            "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_fantastic.jpg",
            "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_fantastic.jpg"
        )

        //Executing
        val textDevolvido =
            character.description.textOrUseThis(message = activity.getString(R.string.message_no_description))

        //Testing
        Assert.assertThat(
            textDevolvido,
            CoreMatchers.`is`(CoreMatchers.equalTo<String>("\"Macacos me mordam Batman!\\n\\nParece que alguém esqueceu de escrever minha descrição.\""))
        )
    }
}