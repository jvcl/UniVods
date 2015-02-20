package au.com.innovus.univods.helper;

import android.provider.BaseColumns;

/**
 * Created by jorge on 20/02/15.
 */
public class UniContract {

    public UniContract() {
    }
    public abstract class MajorEntry implements BaseColumns {

        public static final String TABLE_NAME = "major";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public abstract class TopicEntry implements BaseColumns {

        public static final String TABLE_NAME = "topic";
        public static final String COLUMN_NAME_MAJOR = "major";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_NAME = "name";
    }
}
