package task3.enums;

/**
 * Created by prokop on 9.10.16.
 */
public enum ExceptionMessages {
    STORAGE_IS_FULL {
        @Override
        public String getMessage() {
            return "Storage is full";
        }
    },
    STORAGE_IS_EMPTY {
        @Override
        public String getMessage() {
            return "Storage is empty";
        }
    };

    public abstract String getMessage();
}
