package com.yhb.common.other;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author fusu
 * @since 2021/1/17 4:21 下午
 */
public class NullUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String NULL_STRING = "null";
    public static final int NULL_STRING_LENGTH = "null".length();
    public static final NullUtil NULL_OBJECT = new NullUtil();

    public NullUtil() {
    }

    @Override
    protected final Object clone() {
        return this;
    }

    @Override
    public boolean equals(Object object) {
        return object == null || object == this;
    }

    @Override
    public String toString() {
        return "null";
    }

    public static boolean isNull(String s) {
        if (null != s) {
            int len = s.length();
            if (len > 0) {
                if (len == NULL_STRING_LENGTH && "null".equalsIgnoreCase(s)) {
                    return true;
                }

                return false;
            }
        }

        return true;
    }

    public static boolean isNull(Object o) {
        if (null != o) {
            if (o instanceof String) {
                String s = (String)o;
                int len = s.length();
                if (len > 0) {
                    return len == NULL_STRING_LENGTH && "null".equalsIgnoreCase(s);
                } else {
                    return true;
                }
            } else if (o == NULL_OBJECT) {
                return true;
            } else if (o instanceof Collection) {
                return ((Collection)o).isEmpty();
            } else if (o instanceof Map) {
                return ((Map)o).isEmpty();
            } else if (o.getClass().isArray()) {
                return Array.getLength(o) <= 0;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public static boolean isNotNull(String s) {
        if (null != s) {
            int len = s.length();
            if (len > 0) {
                if (len == NULL_STRING_LENGTH && "null".equalsIgnoreCase(s)) {
                    return false;
                }

                return true;
            }
        }

        return false;
    }

    public static boolean isNotNull(Object o) {
        if (null != o) {
            if (o instanceof String) {
                String s = (String)o;
                int len = s.length();
                if (len > 0) {
                    return len != NULL_STRING_LENGTH || !"null".equalsIgnoreCase(s);
                } else {
                    return false;
                }
            } else if (o == NULL_OBJECT) {
                return false;
            } else if (o instanceof Collection) {
                return !((Collection)o).isEmpty();
            } else if (o instanceof Map) {
                return !((Map)o).isEmpty();
            } else if (o.getClass().isArray()) {
                return Array.getLength(o) > 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean isNullTrim(String s) {
        if (null != s) {
            int len = s.length();
            if (len > 0) {
                if (len == NULL_STRING_LENGTH && "null".equalsIgnoreCase(s)) {
                    return true;
                }

                for(int i = 0; i < len; ++i) {
                    if (!Character.isWhitespace(s.charAt(i))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static boolean isNotNullTrim(String s) {
        if (null != s) {
            int len = s.length();
            if (len > 0) {
                if (len == NULL_STRING_LENGTH && "null".equalsIgnoreCase(s)) {
                    return false;
                }

                for(int i = 0; i < len; ++i) {
                    if (!Character.isWhitespace(s.charAt(i))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static String ifNull(String s, String nullValue, String emptyStrValue, String nullStrValue, String elseValue) {
        if (null != s) {
            int len = s.length();
            if (len > 0) {
                return len == NULL_STRING_LENGTH && "null".equalsIgnoreCase(s) ? nullStrValue : elseValue;
            } else {
                return emptyStrValue;
            }
        } else {
            return nullValue;
        }
    }

    public static String ifNull(String s, String thenValue) {
        return isNull(s) ? thenValue : s;
    }

    public static String ifNull(String s) {
        return isNull(s) ? null : s;
    }

    public static String ifNull(Object o, String nullValue, String emptyStrValue, String nullStrValue, String elseValue) {
        if (null != o) {
            if (o instanceof String) {
                String s = (String)o;
                int len = s.length();
                if (len > 0) {
                    return len == NULL_STRING_LENGTH && "null".equalsIgnoreCase(s) ? nullStrValue : elseValue;
                } else {
                    return emptyStrValue;
                }
            } else {
                return o == NULL_OBJECT ? nullStrValue : elseValue;
            }
        } else {
            return nullValue;
        }
    }

    public static String ifNull(Object o, String thenValue) {
        if (isNull(o)) {
            return thenValue;
        } else {
            return o instanceof String ? (String)o : o.toString();
        }
    }

    public static String ifNull(Object o) {
        if (isNull(o)) {
            return null;
        } else {
            return o instanceof String ? (String)o : o.toString();
        }
    }

    public static String nullIf(String s1, String s2) {
        if (isNull(s1)) {
            if (isNull(s2)) {
                return null;
            }
        } else if (!isNull(s2) && (s1 == s2 || s1.equals(s2))) {
            return null;
        }

        return s1;
    }

    public static String nullIf(Object o1, Object o2) {
        if (isNull(o1)) {
            if (isNull(o2)) {
                return null;
            }
        } else if (!isNull(o2) && (o1 == o2 || o1.equals(o2))) {
            return null;
        }

        if (o1 instanceof String) {
            return (String)o1;
        } else {
            return o1.toString();
        }
    }

    public static boolean equals(String s1, String s2) {
        if (isNull(s1)) {
            if (isNull(s2)) {
                return true;
            }
        } else if (!isNull(s2) && (s1 == s2 || s1.equals(s2))) {
            return true;
        }

        return false;
    }

    public static boolean equals(Object o1, Object o2) {
        if (isNull(o1)) {
            if (isNull(o2)) {
                return true;
            }
        } else if (!isNull(o2) && (o1 == o2 || o1.equals(o2))) {
            return true;
        }

        return false;
    }
}
