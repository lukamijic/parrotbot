package com.parrotbot.chat.ui

import com.parrotbot.chat.ui.adapter.ParrotBotMessage
import com.parrotbot.chat.ui.adapter.UserMessage
import com.parrotbot.coreui.BaseViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ChatViewModel : BaseViewModel<ChatViewState>() {

    init {
        query {
            flowOf(
                listOf(
                    UserMessage(1, "Hey"),
                    ParrotBotMessage(2, "HeY"),
                    UserMessage(3, "How are you today?"),
                    ParrotBotMessage(4, "HoW ArE YoU ToDaY?"),
                    UserMessage(5, "Are you mocking me?"),
                    UserMessage(6, "You are really rude!!!"),
                    ParrotBotMessage(7, "ArE YoU MoCkInG Me?"),
                    ParrotBotMessage(8, "yOu aRE rEALly Rude!!!"),
                    UserMessage(9, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
                    ParrotBotMessage(10, "lOrEM ipSum doLOR Sit Amet, COnSeCTETUR ADIPIsCiNg EliT, sED dO eIUsMOd tEMpoR iNcIDiduNT ut LABorE eT DolORe magnA ALiqua. Ut eNIM Ad mINIM VENIaM, qUIS nOstRuD ExERCITAtiON ulLAmCo LaBoRiS nIsi uT aliQUIp ex ea coMModO COnSEQUaT. dUis aUTE iRURe DOlor iN rePRehendErIT iN VOlUptAte velIT esse CILlum DOLore eU FUgiAT nulla PariAtUr. ExCePTeUr SINt occAecAT CUpiDAtat nON pROIdent, Sunt in cUlPa QUI OFfiCia DESerunt moLLiT aniM Id esT LAboruM.")
                ).reversed()
            ).map { ChatViewState(it) }
        }
    }
}
