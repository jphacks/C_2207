// Generated by view binder compiler. Do not edit!
package com.example.testprog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.testprog.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySubBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnDelete;

  @NonNull
  public final Button btnSave;

  @NonNull
  public final EditText etNote;

  @NonNull
  public final EditText etTitle;

  @NonNull
  public final ListView lvMemoList;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView4;

  private ActivitySubBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnDelete,
      @NonNull Button btnSave, @NonNull EditText etNote, @NonNull EditText etTitle,
      @NonNull ListView lvMemoList, @NonNull TextView textView3, @NonNull TextView textView4) {
    this.rootView = rootView;
    this.btnDelete = btnDelete;
    this.btnSave = btnSave;
    this.etNote = etNote;
    this.etTitle = etTitle;
    this.lvMemoList = lvMemoList;
    this.textView3 = textView3;
    this.textView4 = textView4;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySubBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySubBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sub, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySubBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDelete;
      Button btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.btnSave;
      Button btnSave = ViewBindings.findChildViewById(rootView, id);
      if (btnSave == null) {
        break missingId;
      }

      id = R.id.etNote;
      EditText etNote = ViewBindings.findChildViewById(rootView, id);
      if (etNote == null) {
        break missingId;
      }

      id = R.id.etTitle;
      EditText etTitle = ViewBindings.findChildViewById(rootView, id);
      if (etTitle == null) {
        break missingId;
      }

      id = R.id.lvMemoList;
      ListView lvMemoList = ViewBindings.findChildViewById(rootView, id);
      if (lvMemoList == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      return new ActivitySubBinding((ConstraintLayout) rootView, btnDelete, btnSave, etNote,
          etTitle, lvMemoList, textView3, textView4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
