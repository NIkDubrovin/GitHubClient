package com.nikdubrovin.GithubProjects.data.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NikDubrovin on 23.09.2017.
 */

public class PostRateLimit {

    /**
     *
     * {
         "resources": {
         "core": {
         "limit": 60,
         "remaining": 60,
         "reset": 1506189978
        },
          "search": {
          "limit": 10,
          "remaining": 10,
          "reset": 1506186438
        },
           "graphql": {
           "limit": 0,
            "remaining": 0,
           "reset": 1506189978
         }
     },
            "rate": {
            "limit": 60,
            "remaining": 60,
             "reset": 1506189978
          }
     }
     */

    public PostRateLimit() {}

    @SerializedName("resources")
    @Expose
    private Resources resources;
    @SerializedName("rate")
    @Expose
    private Rate rate;

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public int getRateLimit() {
        return rate.getRemaining();
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

}

class Resources {

    @SerializedName("core")
    @Expose
    private Core core;
    @SerializedName("search")
    @Expose
    private Search search;
    @SerializedName("graphql")
    @Expose
    private Graphql graphql;

    public Core getCore() {
        return core;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public Graphql getGraphql() {
        return graphql;
    }

    public void setGraphql(Graphql graphql) {
        this.graphql = graphql;
    }
}

class Rate {

    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("remaining")
    @Expose
    private int remaining;
    @SerializedName("reset")
    @Expose
    private int reset;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public int getReset() {
        return reset;
    }

    public void setReset(int reset) {
        this.reset = reset;
    }
}

class Core{
        @SerializedName("limit")
        @Expose
        private int limit;
        @SerializedName("remaining")
        @Expose
        private int remaining;
        @SerializedName("reset")
        @Expose
        private int reset;

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getRemaining() {
            return remaining;
        }

        public void setRemaining(int remaining) {
            this.remaining = remaining;
        }

        public int getReset() {
            return reset;
        }

        public void setReset(int reset) {
            this.reset = reset;
        }
}

 class Graphql {

     @SerializedName("limit")
     @Expose
     private int limit;
     @SerializedName("remaining")
     @Expose
     private int remaining;
     @SerializedName("reset")
     @Expose
     private int reset;

     public int getLimit() {
         return limit;
     }

     public void setLimit(int limit) {
         this.limit = limit;
     }

     public int getRemaining() {
         return remaining;
     }

     public void setRemaining(int remaining) {
         this.remaining = remaining;
     }

     public int getReset() {
         return reset;
     }

     public void setReset(int reset) {
         this.reset = reset;
     }
 }

 class Search {

    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("remaining")
    @Expose
    private int remaining;
    @SerializedName("reset")
    @Expose
    private int reset;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public int getReset() {
        return reset;
    }

    public void setReset(int reset) {
        this.reset = reset;
    }

}

