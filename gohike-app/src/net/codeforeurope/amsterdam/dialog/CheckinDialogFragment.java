package net.codeforeurope.amsterdam.dialog;

import net.codeforeurope.amsterdam.NavigateRouteActivity;
import net.codeforeurope.amsterdam.R;
import net.codeforeurope.amsterdam.model.Waypoint;
import net.codeforeurope.amsterdam.util.ApiConstants;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckinDialogFragment extends DialogFragment {

	Waypoint currentTarget;

	public CheckinDialogFragment() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		currentTarget = getArguments().getParcelable(
				ApiConstants.CURRENT_TARGET);

		LayoutInflater inflater = getActivity().getLayoutInflater();

		LinearLayout waypointLayout = (LinearLayout) inflater.inflate(
				R.layout.dialog_checkin, null);
		TextView checkinTitle = (TextView) waypointLayout
				.findViewById(R.id.checkin_title);
		ImageView checkinImage = (ImageView) waypointLayout
				.findViewById(R.id.checkin_image);
		checkinTitle.setText(currentTarget.getLocalizedName());
		Bitmap photo = BitmapFactory.decodeFile(currentTarget.image.localPath);
		checkinImage.setImageBitmap(photo);

		builder.setView(waypointLayout)
				.setTitle(R.string.checkin_close)
				// R.string.pick_color
				// Add action buttons
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								// User wants to check-in
								((NavigateRouteActivity) getActivity())
										.doCheckin();
							}
						})
				.setNegativeButton(R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								getDialog().cancel();

								((NavigateRouteActivity) getActivity()).checkinInProgress = false;
							}
						});

		return builder.create();
	}

}