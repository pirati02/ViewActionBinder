package com.dev.baqari.binding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.dev.baqari.binding.annotation.OnCheckChange;
import com.dev.baqari.binding.annotation.OnClick;
import com.dev.baqari.binding.annotation.OnItemClick;
import com.dev.baqari.binding.annotation.OnItemSelect;
import com.dev.baqari.binding.annotation.OnLongClick;
import com.dev.baqari.binding.annotation.OnScroll;
import com.dev.baqari.binding.annotation.OnSeekChange;
import com.dev.baqari.binding.annotation.OnTextChange;
import com.dev.baqari.binding.annotation.OnTouch;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TypeApplier {

    @Nullable
    public static View applyOnCheckChange(final Method method, OnCheckChange onCheckChange, final Context context) {
        if (onCheckChange.id() > 0) {
            final View view = ((Activity) context).findViewById(onCheckChange.id());
            if (view instanceof CompoundButton) {
                CompoundButton compoundButton = (CompoundButton) view;
                compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        try {
                            int argumentCount = method.getParameterTypes().length;
                            if (argumentCount != 1)
                                throw new Exception("OnCheckChange has one parameter of Type: boolean");
                            else
                                method.invoke(context, b);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                try {
                    throw new Exception("Must provide child of CompoundButton");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return view;
        }
        return null;
    }

    @Nullable
    public static View applyOnClick(final Method method, OnClick onClick, final Context context) {
        if (onClick.id() > 0) {
            final View view = ((Activity) context).findViewById(onClick.id());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        method.invoke(context);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
            return view;
        } else {
            try {
                throw new Exception("not valid widget id");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Nullable
    public static View applyTextChange(final Method method, OnTextChange onTextChange, final Context context) {
        if (onTextChange.id() > 0) {
            final View view = ((Activity) context).findViewById(onTextChange.id());
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {
                            int argumentCount = method.getParameterTypes().length;
                            if (argumentCount != 1)
                                throw new Exception("OnTextChange has one parameter of Type: String");
                            else
                                method.invoke(context, s.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                return view;
            } else {
                try {
                    throw new Exception("Must provide EditText");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Nullable
    public static View applySeekChange(final Method method, OnSeekChange onSeekChange, final Context context) {
        if (onSeekChange.id() > 0) {
            final View view = ((Activity) context).findViewById(onSeekChange.id());
            if (view instanceof SeekBar) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        try {
                            int argumentCount = method.getParameterTypes().length;
                            if (argumentCount != 1)
                                throw new Exception("OnSeekChange has one parameter of Type: int");
                            else
                                method.invoke(context, progress);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            } else {
                try {
                    throw new Exception("Must provide SeekBar");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return view;
        }
        return null;
    }

    @Nullable
    public static View applyOnItemClick(final Method method, OnItemClick onItemClick, final Context context) {
        if (onItemClick.id() > 0) {
            final View view = ((Activity) context).findViewById(onItemClick.id());
            if (view instanceof AbsListView) {
                AbsListView listView = (AbsListView) view;
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            int argumentCount = method.getParameterTypes().length;
                            if (argumentCount != 3)
                                throw new Exception("OnItemClick has 3 parameter of Type: Object, int and View");
                            else
                                method.invoke(context, parent.getAdapter().getItem(position), position, view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                try {
                    throw new Exception("Must provide ListView");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return view;
        }
        return null;
    }

    @Nullable
    public static View applyOnItemSelect(final Method method, OnItemSelect onItemSelect, final Context context) {
        if (onItemSelect.id() > 0) {
            final View view = ((Activity) context).findViewById(onItemSelect.id());
            if (view instanceof AbsListView) {
                AbsListView listView = (AbsListView) view;
                listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                        try {
                            int argumentCount = method.getParameterTypes().length;
                            if (argumentCount != 3)
                                throw new Exception("OnItemSelect has 3 parameter of Type: Object, int and View");
                            else
                                method.invoke(context, parent.getAdapter().getItem(position), position, view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            } else {
                try {
                    throw new Exception("Must provide ListView");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return view;
        }
        return null;
    }

    @Nullable
    public static View applyOnTouch(final Method method, OnTouch onTouch, final Context context) {
        if (onTouch.id() > 0) {
            final View view = ((Activity) context).findViewById(onTouch.id());
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    try {
                        int argumentCount = method.getParameterTypes().length;
                        if (argumentCount != 1)
                            throw new Exception("OnTouch has one parameter of Type MotionEvent");
                        else
                            method.invoke(context, motionEvent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });
            return view;
        } else {
            try {
                throw new Exception("Must provide ListView");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Nullable
    public static View applyOnLongClick(final Method method, OnLongClick onLongClick, final Context context) {
        if (onLongClick.id() > 0) {
            final View view = ((Activity) context).findViewById(onLongClick.id());
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    try {
                        method.invoke(context);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });
            return view;
        } else {
            try {
                throw new Exception("not valid widget id");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Nullable
    public static View applyOnScroll(final Method method, OnScroll onScroll, final Context context) {
        if (onScroll.id() > 0) {
            final View view = ((Activity) context).findViewById(onScroll.id());
            final GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
                @Override
                public boolean onDown(MotionEvent motionEvent) {
                    return false;
                }

                @Override
                public void onShowPress(MotionEvent motionEvent) {

                }

                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {
                    return false;
                }

                @Override
                public boolean onScroll(MotionEvent m1, MotionEvent m2, float v1, float v2) {
                    try {
                        int argumentCount = method.getParameterTypes().length;
                        if (argumentCount != 4)
                            throw new Exception("OnScroll has 4 parameter: first two typeof MotionEvent second two type of float");
                        else
                            method.invoke(context, m1, m2, v1, v2);
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent motionEvent) {

                }

                @Override
                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                    return false;
                }
            });
            view.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    gestureDetector.onTouchEvent(motionEvent);
                    return true;
                }
            });
            return view;
        } else {
            try {
                throw new Exception("Must provide ListView");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
