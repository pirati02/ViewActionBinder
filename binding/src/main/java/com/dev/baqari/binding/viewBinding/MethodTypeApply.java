package com.dev.baqari.binding.viewBinding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;

import com.dev.baqari.binding.viewBinding.annotation.actions.OnCheckChange;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnClick;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnItemClick;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnItemSelect;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnLongClick;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnSeekChange;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnTextChange;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnTouch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MethodTypeApply {

    @SuppressLint("StaticFieldLeak")
    static Context context;

    static View applyOnCheckChange(final Method method, OnCheckChange onCheckChange, final Object object) {
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
                                method.invoke(object, b);
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

    static View applyOnClick(final Method method, OnClick onClick, final Object object) {
        if (onClick.id() > 0) {
            final View view = ((Activity) context).findViewById(onClick.id());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        method.invoke(object);
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

    static View applyTextChange(final Method method, OnTextChange onTextChange, final Object object) {
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
                                method.invoke(object, s.toString());
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

    static View applySeekChange(final Method method, OnSeekChange onSeekChange, final Object object) {
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
                                method.invoke(object, progress);
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

    static View applyOnItemClick(final Method method, OnItemClick onItemClick, final Object object) {
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
                                method.invoke(object, parent.getAdapter().getItem(position), position, view);
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

    static View applyOnItemSelect(final Method method, OnItemSelect onItemSelect, final Object object) {
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
                                method.invoke(object, parent.getAdapter().getItem(position), position, view);
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

    static View applyOnTouch(final Method method, OnTouch onTouch, final Object object) {
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
                            method.invoke(object, motionEvent);
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

    static View applyOnLongClick(final Method method, OnLongClick onLongClick, final Object object) {
        if (onLongClick.id() > 0) {
            final View view = ((Activity) context).findViewById(onLongClick.id());
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    try {
                        method.invoke(object);
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

}
