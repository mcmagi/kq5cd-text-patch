;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include sci.kq5.sh)
(use Main)
(use System)
(use Interface)
(use AudioScript)
(use Timer)

(public
	PrintScript 0
	SayTimer 1
)

(local
	; cdIntro3 -> sceneThreeScript (10103)
	a1s3IntroCount     = 3
	[a1s3IntroData 6]  = [$0310 859  ; "My castle! What has happened?"
	                      $0330 860  ; "I can tell you what happened."
	                      $0350 861] ; "What?!"

	; cdIntro4 -> sceneFourScript (10104)
	a1s4IntroCount     = 2
	[a1s4IntroData 4]  = [$1000 862  ; "I know what happened to your castle. I saw it all. Yes, I did."
	                      $1104 863] ; "You did?! Well, then...what happened?!"

	; cdIntro5 -> sceneFiveScript (10105)
	a1s5IntroCount     = 2
	[a1s5IntroData 4]  = [$1300 864  ; "It was the powerful and evil wizard Mordack who did it. I just happened to be visiting with an old friend when I saw him materialize out of thin air. Thank goodness he didn't notice ME!"
	                      $1404 865] ; "Don't stop now! Go on!"

	; cdIntro6 -> sceneSixScript (10106)
	a1s6IntroCount     = 1
	[a1s6IntroData 2]  = [$1604 866] ; "He conjured up a terrible whirlwind that swirled faster and faster around the castle. With another incantation Mordack then caused the wind to draw the castle up into the sky and out of sight. Oh, it was something to see, all right!"

	; cdIntro4 -> sceneSevenScript (10107)
	a1s7IntroCount    = 2
	[a1s7IntroData 4] = [$1804 867  ; "Why? Why would this wizard...Mordack...want my castle? What could he have against me and my family?"
	                     $1904 868] ; "That I don't know. I only know that it was Mordack who took your castle...and your family."

	; cdIntro5b -> sceneEightScript (10108)
	a1s8IntroCount     = 16
	[a1s8IntroData 32] = [$2100 869  ; "Well, perhaps I can help you. My employer also happens to be a wizard, which is why I recognized Mordack. Unlike Mordack, though, my employer is a very good wizard."
	                       -900 870  ; "His name is Crispinophur...but we all call him Crispin for short. The only problem is, you see...,"
	                      $2400 871  ; "Wh-Whoa...!"
	                      $2600 872  ; "Ahem! Anyway...where was I? Oh yes."
	                      $2700 873  ; "The only problem is that Crispin is getting on in years and tends to be a bit forgetful."
	                      $2800 874  ; "I don't know. This doesn't sound as if it would work."
	                      $2900 875  ; "Oh, sure it would! Crispin is a very qualified wizard...one of the best. He just gets a little forgetful now and again, that's all."
	                      $2950 0    ; (cls)
	                       -120 876  ; "Now where is it? I know I brought it with me. Aha! Here it is!"
	                      $3000 877  ; "What is that?"
	                      $3100 878  ; "Well! It is my opinion that you don't stand a chance against the likes of Mordack."
	                      $3200 879  ; "Excuse me for saying, Your Majesty...but you don't have a choice. You MUST come with me. I'm sure Crispin can help you!"
	                      $3404 880  ; "What is that stuff?"
	                      $3500 881  ; "Oh, just some old, left-over fairy dust I've been carrying around. It'll help you to fly."
	                      $3600 882  ; "You can follow me to the land of Serenia, where Crispin and I live. It's MUCH too far to walk, you know. I think the fairy dust is still good."
	                      $3650 0]   ; (cls)

	; cdIntro -> sceneEightScript (10109)
	a1s9IntroCount     = 2
	[a1s9IntroData 4]  = [$3704 883  ; "Come on! Up here!"
	                      $3904 884] ; "Follow meeeeee...!"

	; cdIntro7 -> a2s2Script (10111)
	a2s2IntroCount     = 4
	[a2s2IntroData 8]  = [$4204 0    ; (cls)
	                        -60 885  ; "There it is! Down here! Come on!"
	                      $4304 886  ; "Okay...here I come!"
	                      $4504 0]   ; (cls)

	; cdIntro9 -> a2s[345]Script (10112)
	a2s34IntroCount     = 8
	[a2s34IntroData 14] = [$4800 887  ; "Looks like the fairy dust just wore off."
	                      $5100 888  ; "Cedric! Where have you been? I've been calling for you!"
	                      $5300 889  ; "Well, well, what have we here?"
	                      $5504 890  ; "A bit clumsy, are you? Well, come on in the house and dry off. No sense sitting around like a wet dog."
	                      $5604 0    ; (cls)
	                      $5800 891  ; "Cedric, go on in the house and pour each of us a nice hot cup of tea."
	                      $5900 892] ; "Aye, aye, Crispin."

	; cdIntro10 -> a2s5Script (10113)
	a2s5IntroCount     = 28
	[a2s5IntroData 56] = [$1100 893  ; "...The Society of Wizards has always taken a dim view of Mordack and his abuse of his power. Why, he's even been put on suspension a few times. It never seems to do any good, though."
	                      $1302 894  ; "Crispin, why would Mordack want to take my family or castle? What did WE ever do to him?"
	                      $1600 895  ; "I'm afraid I don't know the answer to that. Mordack is a very unpredictable wizard. I've NEVER understood that evil mind of his."
	                      $1800 896  ; "I thought perhaps you could help His Majesty, Crispin. That's why I brought him here."
	                      $2000 897  ; "Well, let me see now..."
	                      $2200 898  ; "I used to be a very powerful wizard at one time, you know...but I've gotten a little rusty lately."
	                      $2400 899  ; "A LITTLE rusty!"
	                      $2600 900  ; "That's quite enough from you, Cedric!"
	                      $2704 901  ; "Yes, sir, Crispin...sir."
	                      $2800 0    ; (cls)
	                      $2900 902  ; "I don't know what I have that would be of much use to you. Most of my wizard stuff is pretty old and worn out. But, let's see what I can find."
	                      $3000 0    ; (cls)
	                      $3200 903  ; "No, that won't do!"
	                      $3400 904  ; "That's all used up."
	                      $3600 905  ; "Hmmmm. It might work."
	                      $3800 906  ; "Here, eat this."
	                      $4000 907  ; "What IS that?"
	                      $4100 908  ; "That's an old piece of magical white snake I had left over from last year."
	                      $4200 909  ; "With it, you'll be able to communicate with the natural and animal world. You could find that quite helpful."
	                      $4300 0    ; (cls)
	                      $4400 910  ; "Here's my old wand. I don't even know if it works anymore. Most of its power may be gone. You should know that wands are like pets."
	                      $4500 911  ; "They've got to get to know you before they'll work for you. Just treat it with care and respect and hopefully it will do something for you."
	                      $4700 912  ; "You'd better get going, my boy. No telling WHAT that confounded Mordack could be up to."
	                      $4800 913  ; "You go with him, Cedric. Show him the way."
	                      $4900 914  ; "Who, m-m-me?"
	                      $5000 915  ; "Yes, you! Don't be such a coward. Now, go on! You'd better get started!"
	                      $5100 916  ; "Thank you, sir. I appreciate all you've done for me."
	                      $5200 0]   ; (cls)

	antsLyricCount = 5
	[antsLyricData 10] = [-240 4010  ; "We're the ants led by King Antony"
	                      -210 4011  ; "We're coming to help King Graham!"
	                      -270 4010  ; "We're the ants led by King Antony"
	                      -210 4011  ; "We're coming to help King Graham!"
	                      -270 4012] ; (Whistling)

	willowLyricCount = 4
	[willowLyricData 8] = [-240 4013  ; "How will I find this heart of mine?"
	                       -300 4014  ; "Taken from me for some time"
	                       -300 4015  ; "Here I stand near my pool of tears"
	                       -300 4016] ; "Here I'll stay throughout the years"
)


(class PrintScript of AudioScript
	(properties
		dataPtr null
		dataCount 0
		nextResource -1
	)

	(method (run resource &tmp data count)
		(switch resource
			; song lyrics
			(9998
				(self setScript: (PrintScript new: @antsLyricData antsLyricCount))
			)
			(9999
				(self setScript: (PrintScript new: @willowLyricData willowLyricCount))
			)
			; intro sequence
			(10103
				(self setScript: (PrintScript new: @a1s3IntroData a1s3IntroCount))
			)
			(10104
				(self setScript: (PrintScript new: @a1s4IntroData a1s4IntroCount))
			)
			(10105
				(self setScript: (PrintScript new: @a1s5IntroData a1s5IntroCount))
			)
			(10106
				(self setScript: (PrintScript new: @a1s6IntroData a1s6IntroCount))
			)
			(10107
				(self setScript: (PrintScript new: @a1s7IntroData a1s7IntroCount))
			)
			(10108
				(self setScript: (PrintScript new: @a1s8IntroData a1s8IntroCount))
			)
			(10109
				(self setScript: (PrintScript new: @a1s9IntroData a1s9IntroCount))
			)
			(10111
				(self setScript: (PrintScript new: @a2s2IntroData a2s2IntroCount))
			)
			(10112
				(self setScript: (PrintScript new: @a2s34IntroData a2s34IntroCount))
			)
			(10113
				(self setScript: (PrintScript new: @a2s5IntroData a2s5IntroCount))
			)
			; TODO: ending sequence
		)
	)
	
	(method (new data count)
		(= dataPtr data)
		(= dataCount count)
		(super new:)
	)

	(method (changeState newState &tmp idx time)
		; update current state
		(= state newState)

		; handle resource associated with this cue
		(cond
			((> nextResource 0)
				; print text box
				;(Printf {cue=%x resource=%d} waitForCue nextResource)
				(PrintForAudio nextResource)
			)
			((== nextResource 0)
				; no text - clear screen
				(cls)
			)
		)

		(if (< state dataCount)
			; get next time / resource pair
			(= idx (* state 2))
			(= time (WordAt dataPtr idx))
			(= nextResource (WordAt dataPtr (+ idx 1)))

			(if (> time 0)
				; positive value = cue number to wait for
				(= waitForCue time)
			else
				; negative value = # of ticks to wait for
				(Timer set60ths: self (Abs time))
			)
		else
			; final state - clean things up
			(self dispose:)
		)
	)

	(method (init)
		(gTheDoits add: self)
		(super init: &rest)
	)

	(method (dispose)
		(gTheDoits delete: self)
		(super dispose:)
	)
)


(class SayTimer of Script
	(properties
		seconds 0
		ticks 0
		resource 0
		audio false
	)

	(method (init)
		(= start 0)
		(super init: self)
	)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> seconds 0)
					(Timer set: self seconds)
				else
					(Timer set60ths: self ticks)
				)
			)
			(1
				(if audio
					(Say resource)
				else
					(PrintForAudio resource)
				)
			)
		)
	)
)