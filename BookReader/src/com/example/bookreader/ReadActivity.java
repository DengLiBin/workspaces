package com.example.bookreader;

import org.geometerplus.zlibrary.core.application.ZLApplication;
import org.geometerplus.zlibrary.core.filesystem.ZLFile;
import org.geometerplus.zlibrary.ui.android.library.ZLAndroidActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ReadActivity extends ZLAndroidActivity {

	@Override
	protected ZLApplication createApplication(ZLFile file) {
		return null;
	}

	@Override
	protected ZLFile fileFromIntent(Intent intent) {
		return null;
	}

}
