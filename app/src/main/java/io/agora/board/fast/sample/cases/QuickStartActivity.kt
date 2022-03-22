package io.agora.board.fast.sample.cases

import android.content.pm.ActivityInfo
import android.os.Bundle
import io.agora.board.fast.FastboardView
import io.agora.board.fast.model.FastRegion
import io.agora.board.fast.model.FastRoomOptions
import io.agora.board.fast.sample.Constants
import io.agora.board.fast.sample.R
import io.agora.board.fast.sample.cases.base.BaseActivity
import io.agora.board.fast.sample.misc.Repository

class QuickStartActivity : BaseActivity() {
    private val repository = Repository.get()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_start)
        supportActionBar!!.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setupFastboard()
    }

    private fun setupFastboard() {
        // obtain fastboard
        val fastboardView = findViewById<FastboardView>(R.id.fastboard_view)
        val fastboard = fastboardView.fastboard

        // set room options
        val roomOptions = FastRoomOptions(
            Constants.SAMPLE_APP_ID,
            intent.getStringExtra(Constants.KEY_ROOM_UUID),
            intent.getStringExtra(Constants.KEY_ROOM_TOKEN),
            repository.userId,
            FastRegion.CN_HZ,
            false,
        )

        // join room
        val fastRoom = fastboard.createFastRoom(roomOptions)
        fastRoom.join()
    }

    override fun onResume() {
        super.onResume()
        setupFullScreen()
    }
}