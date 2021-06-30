package org.mobilitydata.gtfsvalidator.domain.entity.gtfs;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mobilitydata.gtfsvalidator.domain.entity.notice.base.Notice;
import org.mobilitydata.gtfsvalidator.domain.entity.notice.error.MissingRequiredValueNotice;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for all entities defined in levels.txt. Can not be directly instantiated: user must use the
 * {@code Level.LevelBuilder} to create this.
 */
public class Level extends GtfsEntity {
    @NotNull
    private final String levelId;
    @NotNull
    private final Float levelIndex;
    @Nullable
    private final String levelName;

    /**
     * @param levelId    id of the level that can be referenced from stops.txt
     * @param levelIndex numeric index of the level that indicates relative position of this level in relation to other
     *                   levels
     * @param levelName  optional name of the level
     */
    private Level(@NotNull final String levelId,
                  @NotNull Float levelIndex,
                  @Nullable final String levelName) {
        this.levelId = levelId;
        this.levelIndex = levelIndex;
        this.levelName = levelName;
    }

    @NotNull
    public String getLevelId() {
        return levelId;
    }

    @NotNull
    public Float getLevelIndex() {
        return levelIndex;
    }

    @Nullable
    public String getLevelName() {
        return levelName;
    }

    /**
     * Builder class to create {@link Level} objects. Allows an unordered definition of the different attributes of
     * {@link Level}.
     */
    public static class LevelBuilder {
        private String levelId;
        private Float levelIndex;
        private String levelName;
        private final List<Notice> noticeCollection = new ArrayList<>();

        /**
         * Sets field levelId value and returns this
         *
         * @param levelId id of the level that can be referenced from stops.txt
         * @return builder for future object creation
         */
        public LevelBuilder levelId(@NotNull final String levelId) {
            this.levelId = levelId;
            return this;
        }

        /**
         * Sets field levelIndex value and returns this
         *
         * @param levelIndex numeric index of the level that indicates relative position of this level in relation to other
         *                   levels
         * @return builder for future object creation
         */
        public LevelBuilder levelIndex(@NotNull final Float levelIndex) {
            this.levelIndex = levelIndex;
            return this;
        }

        /**
         * Sets field levelName value and returns this
         *
         * @param levelName optional name of the level
         * @return builder for future object creation
         */
        public LevelBuilder levelName(@Nullable final String levelName) {
            this.levelName = levelName;
            return this;
        }

        /**
         * Return {@code EntityBuildResult} representing a row from levels.txt if the requirements from the official
         * GTFS specification are met. Otherwise, method returns an entity representing a collection of notices
         * specifying the issues.
         *
         * @return {@code EntityBuildResult} representing a row from levels.txt if the requirements from the official
         * GTFS specification are met. Otherwise, method returns an entity representing a collection of notices.
         */
        public EntityBuildResult<?> build() {
            if (levelId == null || levelIndex == null) {
                if (levelId == null) {
                    noticeCollection.add(new MissingRequiredValueNotice("levels.txt", "level_id",
                            levelId));
                }
                if (levelIndex == null) {
                    noticeCollection.add(new MissingRequiredValueNotice("levels.txt", "level_index",
                            levelId));
                }
                return new EntityBuildResult<>(noticeCollection);
            } else {
                return new EntityBuildResult<>(new Level(levelId, levelIndex, levelName));
            }
        }

        /**
         * Method to reset all fields of builder. Returns builder with all fields set to null.
         *
         * @return builder with all fields set to null;
         */
        public LevelBuilder clear() {
            levelId = null;
            levelIndex = null;
            levelName = null;
            noticeCollection.clear();
            return this;
        }
    }
}