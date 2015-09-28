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

  (cellos->armadillos \"Oh look, there's a purple cello!\") =>
  \"Oh look, there's a purple armadillo!\""
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

(midje/fact
 "say-hi-to-an-armadillo greets an armadillo, assuming that armadillo can read"
 (say-hi-to-an-armadillo) => "hi, armadillo")
