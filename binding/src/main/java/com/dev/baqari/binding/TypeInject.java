package com.dev.baqari.binding;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;

import com.dev.baqari.binding.annotation.OnClick;
import com.dev.baqari.binding.annotation.OnItemClick;
import com.dev.baqari.binding.annotation.OnSeekChange;
import com.dev.baqari.binding.annotation.OnTextChange;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TypeInject {

    @Nullable
    public static View applyType(final Method method, Annotation annotation, final Context context) {
        if (annotation instanceof OnClick) {
            final OnClick onclick = method.getAnnotation(OnClick.class);
            if (onclick.id() > 0) {
                final View view = ((Activity) context).findViewById(onclick.id());
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
        } else if (method.isAnnotationPresent(OnTextChange.class)) {
            final OnTextChange textChange = method.getAnnotation(OnTextChange.class);
            if (textChange.id() > 0) {
                final View view = ((Activity) context).findViewById(textChange.id());
                if (view instanceof EditText) {
                    EditText editText = (EditText) view;
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            try {
                                method.invoke(context, s.toString());
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
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
        } else if (method.isAnnotationPresent(OnSeekChange.class)) {
            final OnSeekChange seekChange = method.getAnnotation(OnSeekChange.class);
            if (seekChange.id() > 0) {
                final View view = ((Activity) context).findViewById(seekChange.id());
                if (view instanceof SeekBar) {
                    SeekBar seekBar = (SeekBar) view;
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            try {
                                method.invoke(context, progress);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
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
        } else if (method.isAnnotationPresent(OnItemClick.class)) {
            final OnItemClick onItemClick = method.getAnnotation(OnItemClick.class);
            if (onItemClick.id() > 0) {
                final View view = ((Activity) context).findViewById(onItemClick.id());
                if (view instanceof AbsListView) {
                    AbsListView listView = (AbsListView) view;
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                method.invoke(context, parent.getAdapter().getItem(position), position, view);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
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
        }
        return null;
    }
}
