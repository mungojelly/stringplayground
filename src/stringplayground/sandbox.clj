(ns stringplayground.sandbox
  (:require
    [midje.sweet :as midje]))

(defn hello
  "Says hello to the specified world.

  Usage example: 

  (hello \"cello\") => \"hi, cello\""
  [world]
  (str "hi, " world))

(midje/fact
 "hello puts hi, a comma and a space in front of a string"
 (hello "cello") => "hi, cello")

(defn cellos->armadillos
  "Given a string, returns a string which is the same 
  except that every occurrence of \"cello\" has been 
  replaced with \"armadillo\".

  Usage example:

  (cellos->armadillos \"Oh look, there's a purple cello!\") 
  => \"Oh look, there's a purple armadillo!\""
  [s]
  (clojure.string/replace s #"cello" "armadillo"))

(midje/fact
  "cellos->armadillos replaces every 'cello' with 'armadillo'"
  (cellos->armadillos "Oh look, there's a purple cello!") =>
  "Oh look, there's a purple armadillo!"
  (cellos->armadillos "cello cello mello cello") =>
  "armadillo armadillo mello armadillo")

(midje/fact
 "cellos->armadillos leaves strings without any cellos unchanged"
 (cellos->armadillos "this is just any old string") =>
 "this is just any old string")

(defn say-hi-to-an-armadillo
  "Returns an armadillo greeting.

  Usage example:

  (say-hi-to-an-armadillo) => \"hi, armadillo\""
  []
  (cellos->armadillos (hello "cello")))

(midje/fact "say-hi-to-an-armadillo greets an armadillo"
 (say-hi-to-an-armadillo) => "hi, armadillo")

(defn remove-newlines
  "Removes all newlines from a string.

  Usage example:

  (remove-newlines \"new\\nlines\") => \"newlines\""
  [s]
  (clojure.string/replace s #"\n" ""))

(midje/fact
 "remove-newlines removes newlines from a string"
  (remove-newlines "\n\n\n") => ""
  (remove-newlines "stays the same")
                => "stays the same"
  (remove-newlines "this is a \ntest")
                => "this is a test"
  (remove-newlines "removes more \nthan one\n newline")
                => "removes more than one newline"
  (remove-newlines "removes newline at end\n")
                => "removes newline at end")

(defn force-line-length
  "Removes all newlines from a string and then 
  hard wraps it to the specified line length.

  (force-line-length 3 \"te\\nst\\nin\\ng\") => \"tes\\ntin\\ng\""
  [partition-length input-string]
  (let [unbroken (remove-newlines input-string)]
    (clojure.string/join
     "\n"
     (map (partial apply str)
          (partition-all partition-length unbroken)))))

(midje/fact
 "force-line-length hard wraps strings"
 (force-line-length 3 "hi this is a test")
 => "hi \nthi\ns i\ns a\n te\nst")

(midje/fact
 "force-line-length strips existing newlines"
 (force-line-length 5 "hi\n new\nlines\n\n\n")
 => "hi ne\nwline\ns")
