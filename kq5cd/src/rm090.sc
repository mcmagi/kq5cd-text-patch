;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 90)
(include sci.sh)
(use Main)
(use Interface)
(use KQ5Room)
(use RandCycle)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Path)
(use Motion)
(use Actor)
(use System)

(public
	rm090 0
)

(local
	[local0 7] = [0 94 36 115 68 126 -32768]
	[local7 15] = [68 126 99 124 146 108 165 96 181 81 193 71 201 62 -32768]
	local22
	[local23 16] = [0 168 92 168 143 152 173 150 137 0 319 0 319 189 0 189]
	[local39 8] = [0 0 134 0 125 141 0 151]
)

(procedure (localproc_0)
	(cls)
	(h1Mouth cue:)
	(h2Mouth cue:)
	(h3Mouth cue:)
	(h4Mouth cue:)
)

(instance rm090 of KQ5Room
	(properties
		picture 90
		west 50
	)

	(method (init)
		(super init:)
		(LoadMany rsVIEW 836 838 54 70)
		(switch gPrevRoomNum
			(91
				(Load rsVIEW 840)
				(harpy1 setScript: argueScript)
			)
			(50
				(if (IsFlag 55)
					(gGlobalSound number: 814 loop: -1 playBed:)
				)
				(gEgo
					init:
					view: (if (IsFlag 74) 659 else 2)
					posn: 40 162
					setStep: 2 2
				)
				(if (== (gEgo view:) 659)
					((gEgo head:) hide:)
				)
				(if (IsFlag 54)
					(harpy1 init: posn: -50 20 setScript: harpyInitScript)
				)
			)
			(else
				(Load rsVIEW 840)
				(gGlobalSound number: 816 loop: -1 playBed:)
				(self setScript: crtn5Script)
			)
		)
		(if (not (gEgo has: 31)) ; Fishhook
			(fishhook init: setScript: hookScript)
		)
		(self setFeatures: island room addObstacle: poly1 poly2)
		(poly1 points: @local23 size: 8)
		(poly2 points: @local39 size: 4)
	)

	(method (doit &tmp temp0)
		(cond
			(script
				(script doit:)
			)
			((= temp0 (self edgeToRoom: (gEgo edgeHit:)))
				(gCurRoom newRoom: temp0)
			)
			((& (gEgo onControl: 0) $0010)
				(HandsOff)
				(self setScript: fallScript)
			)
		)
	)

	(method (handleEvent event &tmp [temp0 100])
		(cond
			((event claimed:)
				(return)
			)
			(script
				(return)
			)
			(
				(and
					(== (event type:) evVERB)
					(MousedOn gEgo event)
					(== (event message:) 4) ; Inventory
				)
				(switch (gInventory indexOf: (gTheIconBar curInvIcon:))
					(10
						(gCurRoom setScript: playHarpScript)
						(event claimed: 1)
					)
					(28
						(event claimed: 0)
					)
					(else
						(Say 753)
						(event claimed: 1)
					)
				)
			)
		)
	)

	(method (dispose)
		(DisposeScript 983)
		(DisposeScript 941)
		(super dispose:)
	)
)

(instance harpyInitScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (Random 1 100) 50)
					0
				else
					(self dispose:)
				)
			)
		)
	)

	(method (doit)
		(super doit:)
		(if (> (gEgo x:) 88)
			(HandsOff)
			(gCurRoom setScript: harpyScript)
			(self dispose:)
		)
	)
)

(instance harpyScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGlobalSound number: 815 loop: -1 playBed: 90)
				(harpy1
					view: 836
					setLoop: 0
					ignoreActors: 1
					setCycle: Fwd
					setMotion: MoveTo (gEgo x:) (- (gEgo y:) 30) self
				)
			)
			(1
				(if (IsObject (gEgo head:))
					((gEgo head:) dispose:)
				)
				(gEgo normal: 0 view: 838 setLoop: 0 setCycle: Fwd)
				(harpy1 setMotion: MoveTo 350 20 self)
			)
			(2
				(= global103 0)
				(= cycles 1)
			)
			(3
				(= global330 744)
				(EgoDead 247 0 End)
			)
		)
	)

	(method (doit)
		(if state
			(gEgo posn: (- (harpy1 x:) 5) (- (harpy1 y:) 3))
		)
		(super doit:)
	)
)

(instance fallScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: (if (IsFlag 74) 838 else 70)
					setLoop: (if (IsFlag 74) 5 else 0)
					cel: 0
					cycleSpeed: 1
					setCycle: End self
					setPri: 1
					illegalBits: 0
				)
				(gGlobalAudio number: 7053 play:)
				(gGlobalSound2 number: 83 loop: 1 play:)
			)
			(1
				(gEgo
					yStep: 8
					setMotion: MoveTo (- (gEgo x:) 20) 230 self
				)
			)
			(2
				(= global103 0)
				(gGlobalAudio number: 8068 play:)
				(= seconds 2)
			)
			(3
				(= global330 412)
				(EgoDead)
			)
		)
	)
)

(instance crtn5Script of Script
	(properties)

	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(DrawCel 2 0 0 400 0 0 3)
				(harpy2 init:)
				(harpy3 init:)
				(h3Mouth init:)
				(gEgo
					view: 838
					setLoop: 0
					x: -100
					setCycle: Fwd
					normal: 0
					init:
				)
				((gEgo head:) hide:)
				(harpy4 init: setCycle: Fwd setMotion: swoop1 self)
			)
			(1
				(harpy4 setMotion: swoop2 self)
				(gEgo
					posn: (+ (gEgo x:) 18) (+ (gEgo y:) 31)
					setLoop: 1
					setCel: 0
					cycleSpeed: 2
					setCycle: End self
				)
				(gEgo setMotion: MoveTo (+ (gEgo x:) 20) (gEgo y:))
			)
			(2)
			(3
				(harpy1 init: setScript: harpy1Script)
				(= cycles 1)
			)
			(4
				(gEgo
					view: 2
					posn: (+ (gEgo x:) 29) (gEgo y:)
					setMotion: 0
					normal: 1
					loop: 7
					cel: 0
				)
				((gEgo head:) show:)
				(harpy4 view: 840 setLoop: 5 setCel: 1 setPri: 9 setCycle: 0)
				(h4Mouth init:)
				(self dispose:)
			)
		)
	)

	(method (doit)
		(if (not state)
			(gEgo posn: (- (harpy4 x:) 5) (harpy4 y:))
		)
		(super doit:)
	)
)

(instance harpy1Script of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(harpy1 init: setCycle: Fwd setMotion: MoveTo 66 127 self)
			)
			(1
				(harpy1 setLoop: 3 setCel: 0 setCycle: End self)
			)
			(2
				(harpy1 setMotion: MoveTo 66 136 self)
			)
			(3
				(harpy1
					view: 840
					loop: 0
					posn: (+ (harpy1 x:) 3) (+ (harpy1 y:) 25)
				)
				(= seconds 3)
			)
			(4
				(gCurRoom newRoom: 91)
			)
		)
	)
)

(instance argueScript of Script
	(properties)

	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOn)
				(= global103 0)
				(gEgo init:)
				(NormalEgo 11 2)
				(gEgo
					cel: 0
					setStep: 2 2
					posn: 126 156
					init:
					view: 2
					loop: 7
				)
				(harpy1 view: 840 posn: 69 161 setLoop: 0 setCel: 1 init:)
				(harpy2 init: setCel: 1)
				(harpy3 init:)
				(harpy4
					view: 840
					setLoop: 5
					setCel: 1
					posn: 201 62
					init:
					setPri: 9
				)
				(h1Mouth init:)
				(h2Mouth init:)
				(h3Mouth init:)
				(h4Mouth init:)
				(= cycles 1)
			)
			(1
				(h2Mouth cue: 1)
				(Say 5060 self)
			)
			(2
				(localproc_0)
				(h4Mouth cue: 1)
				(Say 5061 self)
			)
			(3
				(localproc_0)
				(h3Mouth cue: 1)
				(Say 5062 self)
			)
			(4
				(localproc_0)
				(h4Mouth cue: 1)
				(Say 5063 self)
			)
			(5
				(localproc_0)
				(h1Mouth cue: 1)
				(Say 5064 self)
			)
			(6
				(localproc_0)
				(h2Mouth cue: 1)
				(Say 5065 self)
			)
			(7
				(localproc_0)
				(h3Mouth cue: 1)
				(Say 5066 self)
			)
			(8
				(localproc_0)
				(h2Mouth cue: 1)
				(Say 5067 self)
			)
			(9
				(localproc_0)
				(h3Mouth cue: 1)
				(Say 5068 self)
			)
			(10
				(localproc_0)
				(h2Mouth cue: 1)
				(Say 5069 self)
			)
			(11
				(localproc_0)
				(h3Mouth cue: 1)
				(Say 5070 self)
			)
			(12
				(localproc_0)
				(h1Mouth cue: 1)
				(Say 5071 self)
			)
			(13
				(localproc_0)
				(HandsOff)
				(client setScript: 0)
				(gCurRoom setScript: grabEgoScript)
			)
		)
	)

	(method (doit)
		(if (< (gEgo x:) (+ (harpy1 x:) 20))
			(HandsOff)
			(gCurRoom setScript: escapeScript)
		)
		(super doit: &rest)
	)
)

(instance escapeScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_0)
				(harpy1 setScript: 0)
				(h1Mouth cue: 1)
				(Say 5077 self)
			)
			(1
				(localproc_0)
				(h1Mouth dispose:)
				(harpy1 view: 842 setLoop: 0 cel: 0 setCycle: End self)
			)
			(2
				(if (IsObject (gEgo head:))
					((gEgo head:) dispose:)
				)
				(gEgo dispose:)
				(harpy1 setLoop: 2 cel: 0 setCycle: End self)
			)
			(3
				(harpy1
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo -30 (harpy1 y:)
				)
				(h2Mouth dispose:)
				(harpy2
					view: 836
					setLoop: 5
					ignoreActors:
					setCel: 0
					posn: (- (harpy2 x:) 25) (- (harpy2 y:) 20)
					setCycle: End self
				)
			)
			(4
				(harpy3 setScript: harpy3Script)
				(harpy4 setScript: harpy4Script self)
				(harpy2 setCycle: Fwd setLoop: 2 setMotion: MoveTo -40 140)
			)
			(5
				(= global103 0)
				(= cycles 2)
			)
			(6
				(= global330 744)
				(EgoDead 247 0 End)
			)
		)
	)
)

(instance grabEgoScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_0)
				(h2Mouth dispose:)
				(harpy2
					view: 836
					setLoop: 5
					ignoreActors:
					setCel: 0
					posn: (- (harpy2 x:) 25) (- (harpy2 y:) 20)
					setCycle: End self
				)
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 838
					setLoop: 3
					posn: (- (gEgo x:) 18) (- (gEgo y:) 34)
					setCel: 0
					illegalBits: 0
					ignoreHorizon: 1
				)
			)
			(1
				(harpy2
					setLoop: 2
					setCycle: Fwd
					setPri: (+ (gEgo priority:) 1)
					setMotion:
						MoveTo
						(+ (gEgo x:) 17)
						(+ (gEgo y:) 6)
						self
				)
			)
			(2
				(gEgo setCycle: End self)
			)
			(3
				(gEgo setLoop: 4 setCel: 0 setCycle: Fwd)
				(harpy2 setLoop: 2 setMotion: MoveTo -40 70 self)
			)
			(4
				(localproc_0)
				(h1Mouth dispose:)
				(harpy1 setLoop: 4 setCycle: End self)
			)
			(5
				(harpy3 setScript: harpy3Script)
				(harpy4 setScript: harpy4Script self)
				(harpy1
					view: 836
					setLoop: 5
					setCel: 0
					posn: (- (harpy1 x:) 25) (- (harpy1 y:) 20)
					setCycle: End self
				)
			)
			(6
				(harpy1 setLoop: 2 setCycle: Fwd setMotion: MoveTo -40 70)
			)
			(7
				(= global103 0)
				(= cycles 2)
			)
			(8
				(= global330 744)
				(EgoDead 247 0 End)
			)
		)
	)

	(method (doit)
		(if (> state 2)
			(gEgo posn: (- (harpy2 x:) 4) (- (harpy2 y:) 1))
		)
		(super doit: &rest)
	)
)

(instance playHarpScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo put: 10) ; Harp
				(SetScore 4)
				(HandsOff)
				(harpy1 setScript: 0)
				(localproc_0)
				(note setScript: noteScript init:)
				(harp posn: (gEgo x:) (gEgo y:) 1000 init:)
				((gEgo head:) view: 54 loop: 1)
				(gEgo
					normal: 0
					view: 54
					loop: 0
					cel: 0
					cycleSpeed: (if (== gHowFast 0) 0 else 5)
					posn: (+ (gEgo x:) 1) (+ (gEgo y:) 2)
					setCycle: RandCycle 20 self
				)
				(gGlobalSound number: 817 loop: -1 playBed:)
			)
			(1
				(gEgo setCycle: RandCycle)
				(h3Mouth cue: 1)
				(Say 5072 self)
			)
			(2
				(localproc_0)
				(h2Mouth cue: 1)
				(Say 5073 self)
			)
			(3
				(localproc_0)
				(h2Mouth dispose:)
				(harpy2
					view: 836
					setLoop: 5
					setCel: 0
					posn: (- (harpy2 x:) 25) (- (harpy2 y:) 20)
					setCycle: End self
				)
			)
			(4
				(harpy2
					setLoop: 2
					setCycle: Fwd
					setPri: (+ (gEgo priority:) 1)
					setMotion: MoveTo (gEgo x:) (- (gEgo y:) 26) self
				)
			)
			(5
				(note dispose:)
				(gEgo setLoop: 2 cycleSpeed: 2 setCycle: End self)
				(gGlobalSound stop:)
			)
			(6
				(harpy2 setMotion: MoveTo -40 70 self)
				(harp z: 0 setLoop: 3)
				(gEgo
					normal: 1
					view: 2
					illegalBits: $8000
					loop: 4
					setLoop: -1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					setStep: 2 2
					observeControl: 8
					observeControl: 2048
				)
				((gEgo head:) view: 2 loop: 4)
			)
			(7
				(h1Mouth cue: 1)
				(h3Mouth cue: 1)
				(h4Mouth cue: 1)
				(Say 5074 self)
			)
			(8
				(localproc_0)
				(h1Mouth dispose:)
				(harpy1 setLoop: 4 setCycle: End self)
			)
			(9
				(harpy3 setScript: harpy3Script)
				(harpy4 setScript: harpy4Script self)
				(harpy1
					view: 836
					setLoop: 5
					setCel: 0
					posn: (- (harpy1 x:) 25) (- (harpy1 y:) 20)
					setCycle: End self
				)
			)
			(10
				(harpy1 setLoop: 2 setCycle: Fwd setMotion: MoveTo -40 70)
			)
			(11
				(gGlobalSound number: 814 loop: -1 playBed:)
				(SetFlag 55)
				(harpy1 dispose:)
				(harpy2 dispose:)
				(harpy3 dispose:)
				(harpy4 dispose:)
				(self dispose:)
			)
		)
	)

	(method (doit)
		(if (> state 3)
			(harp posn: (- (harpy2 x:) 1) (+ (harpy2 y:) 3))
		)
		(super doit:)
	)
)

(instance noteScript of Script
	(properties)

	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(note
					x: (+ (gEgo x:) 5)
					y: (- (gEgo y:) 20)
					setCel: (Random 0 3)
					setMotion:
						MoveTo
						(+ (gEgo x:) 25)
						(- (gEgo y:) 40)
						self
				)
			)
			(1
				(self init:)
			)
		)
	)
)

(instance harpy3Script of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(h3Mouth dispose:)
				(harpy3
					view: 836
					setLoop: 6
					posn: (- (harpy3 x:) 3) (- (harpy3 y:) 20)
					setCycle: End self
				)
			)
			(1
				(harpy3
					setLoop: 2
					posn: (- (harpy3 x:) 35) (- (harpy3 y:) 19)
					setCycle: Fwd
					setMotion: MoveTo -40 10 self
				)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance harpy4Script of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 20)
			)
			(1
				(h4Mouth dispose:)
				(harpy4
					view: 836
					setLoop: 6
					posn: (- (harpy4 x:) 3) (- (harpy4 y:) 20)
					setCycle: End self
				)
			)
			(2
				(harpy4
					setLoop: 2
					posn: (- (harpy4 x:) 35) (- (harpy4 y:) 19)
					setCycle: Fwd
					setMotion: MoveTo -40 10 self
				)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance wrongThingScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_0)
				(h3Mouth cue: 1)
				(Say 5075 self)
			)
			(1
				(localproc_0)
				(h4Mouth cue: 1)
				(Say 5076 self)
			)
			(2
				(localproc_0)
				(argueScript start: local22)
				(harpy1 setScript: argueScript)
				(= local22 1000)
				(HandsOn)
			)
		)
	)
)

(instance hookScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 5)
			)
			(1
				(fishhook setCycle: End self)
			)
			(2
				(fishhook setCycle: 0)
				(self init:)
			)
		)
	)
)

(instance getHookScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 665)
				(gEgo
					setMotion:
						PolyPath
						(+ (fishhook x:) 10)
						(+ (fishhook y:) 1)
						self
				)
			)
			(1
				(if (gCast contains: harpy1)
					(Say 751)
				else
					(Say 750)
				)
				(= register (gEgo view:))
				(if (== (gEgo view:) 2)
					((gEgo head:) hide:)
				)
				(gEgo
					normal: 0
					view: 665
					setLoop: (if (IsFlag 74) 3 else 4)
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(2
				(SetScore 2)
				(fishhook dispose:)
				(gEgo setCycle: End self)
			)
			(3
				(gEgo
					normal: 1
					view: register
					setLoop: -1
					loop: 3
					setCycle: KQ5SyncWalk
					get: 31 ; Fishhook
				)
				(if (== (gEgo view:) 2)
					((gEgo head:) show:)
				)
				(= cycles 3)
			)
			(4
				(HandsOn)
				(= global103 0)
				(self dispose:)
			)
		)
	)
)

(instance harpy1 of Actor
	(properties
		x -10
		y 120
		yStep 7
		view 836
		priority 10
		signal 26640
		cycleSpeed 1
		illegalBits 0
		xStep 7
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (MousedOn self event))
				(not (== (event type:) evVERB))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(cls)
					(Say 745)
					(event claimed: 1)
				)
				(3 ; Do
					(cls)
					(Say 752)
					(event claimed: 1)
				)
				(5 ; Talk
					(cls)
					(Say 755)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance harpy2 of Actor
	(properties
		x 211
		y 152
		yStep 7
		view 840
		loop 9
		priority 12
		signal 26640
		cycleSpeed 1
		illegalBits 0
		xStep 7
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (MousedOn self event))
				(not (== (event type:) evVERB))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(cls)
					(Say 745)
					(event claimed: 1)
				)
				(3 ; Do
					(cls)
					(Say 752)
					(event claimed: 1)
				)
				(5 ; Talk
					(cls)
					(Say 755)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance harpy3 of Actor
	(properties
		x 180
		y 61
		yStep 7
		view 840
		loop 5
		priority 10
		signal 26640
		cycleSpeed 1
		illegalBits 0
		xStep 7
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (MousedOn self event))
				(not (== (event type:) evVERB))
			)
			(return)
		else
			(harpy1 handleEvent: event)
			(switch (event message:)
				(2 ; Look
					(cls)
					(Say 745)
					(event claimed: 1)
				)
				(3 ; Do
					(cls)
					(Say 752)
					(event claimed: 1)
				)
				(5 ; Talk
					(cls)
					(Say 755)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance harpy4 of Actor
	(properties
		x -10
		y 125
		yStep 7
		view 836
		priority 11
		signal 26640
		cycleSpeed 1
		illegalBits 0
		xStep 7
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (MousedOn self event))
				(not (== (event type:) evVERB))
			)
			(return)
		else
			(switch (event message:)
				(harpy1
					species
					event
				)
				(2 ; Look
					(cls)
					(Say 745)
					(event claimed: 1)
				)
				(3 ; Do
					(cls)
					(Say 752)
					(event claimed: 1)
				)
				(5 ; Talk
					(cls)
					(Say 755)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance h1Mouth of Prop
	(properties
		view 840
		loop 1
		signal 26640
		cycleSpeed 3
	)

	(method (init)
		(super init: &rest)
		(self
			posn: (+ (harpy1 x:) 4) (- (harpy1 y:) 28)
			priority: (+ (harpy1 priority:) 1)
		)
		(h1Arms init:)
	)

	(method (cue)
		(super cue: &rest)
		(if (not argc)
			(self setCycle: 0)
			(h1Arms setCycle: 0)
		else
			(self setCycle: RandCycle)
			(h1Arms setCycle: RandCycle)
		)
	)

	(method (dispose)
		(self setCycle: 0)
		(h1Arms dispose:)
		(super dispose:)
	)
)

(instance h2Mouth of Prop
	(properties
		view 840
		loop 10
		signal 26640
		cycleSpeed 3
	)

	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy2 x:) 9) (- (harpy2 y:) 18)
			priority: (+ (harpy2 priority:) 1)
		)
		(h2Arms init:)
	)

	(method (cue)
		(super cue: &rest)
		(if (not argc)
			(self setCycle: 0)
			(h2Arms setCycle: 0)
		else
			(self setCycle: RandCycle)
			(h2Arms setCycle: RandCycle)
		)
	)

	(method (dispose)
		(self setCycle: 0)
		(h2Arms dispose:)
		(super dispose:)
	)
)

(instance h3Mouth of Prop
	(properties
		view 840
		loop 6
		signal 26640
		cycleSpeed 3
	)

	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy3 x:) 6) (- (harpy3 y:) 20)
			priority: (+ (harpy3 priority:) 1)
		)
		(h3Arms init:)
	)

	(method (cue)
		(super cue: &rest)
		(if (not argc)
			(self setCycle: 0)
			(h3Arms setCycle: 0)
		else
			(self setCycle: RandCycle)
			(h3Arms setCycle: RandCycle)
		)
	)

	(method (dispose)
		(self setCycle: 0)
		(h3Arms dispose:)
		(super dispose:)
	)
)

(instance h4Mouth of Prop
	(properties
		view 840
		loop 6
		signal 26640
		cycleSpeed 3
	)

	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy4 x:) 7) (- (harpy4 y:) 21)
			priority: (+ (harpy4 priority:) 1)
		)
		(h4Arms init:)
	)

	(method (cue)
		(super cue: &rest)
		(if (not argc)
			(self setCycle: 0)
			(h4Arms setCycle: 0)
		else
			(self setCycle: RandCycle)
			(h4Arms setCycle: RandCycle)
		)
	)

	(method (dispose)
		(self setCycle: 0)
		(h4Arms dispose:)
		(super dispose:)
	)
)

(instance h1Arms of Prop
	(properties
		view 840
		loop 2
		signal 26640
		cycleSpeed 3
	)

	(method (init)
		(super init: &rest)
		(self
			posn: (+ (harpy1 x:) 1) (- (harpy1 y:) 23)
			priority: (+ (harpy1 priority:) 1)
		)
	)
)

(instance h2Arms of Prop
	(properties
		view 840
		loop 11
		signal 26640
		cycleSpeed 3
	)

	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy2 x:) 9) (- (harpy2 y:) 19)
			priority: (+ (harpy2 priority:) 1)
		)
	)
)

(instance h3Arms of Prop
	(properties
		view 840
		loop 7
		signal 26640
		cycleSpeed 3
	)

	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy3 x:) 7) (- (harpy3 y:) 17)
			priority: (+ (harpy3 priority:) 1)
		)
	)
)

(instance h4Arms of Prop
	(properties
		view 840
		loop 8
		signal 26640
		cycleSpeed 3
	)

	(method (init)
		(super init: &rest)
		(self
			posn: (- (harpy4 x:) 1) (- (harpy4 y:) 18)
			priority: (+ (harpy4 priority:) 1)
		)
	)
)

(instance swoop1 of Path
	(properties)

	(method (at param1)
		(return [local0 param1])
	)
)

(instance swoop2 of Path
	(properties)

	(method (at param1)
		(return [local7 param1])
	)
)

(instance harp of Prop
	(properties
		view 54
		loop 4
		signal 26624
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (MousedOn self event))
				(not (== (event type:) evVERB))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(if (gCast contains: harpy2)
						(Say 746)
					else
						(Say 747)
					)
					(event claimed: 1)
				)
				(4 ; Inventory
					(switch (gInventory indexOf: (gTheIconBar curInvIcon:))
						(10
							(gCurRoom setScript: playHarpScript)
							(event claimed: 1)
						)
						(28
							(event claimed: 0)
						)
						(else
							(cond
								((not (gCast contains: harpy2)) 0)
								((== local22 1000)
									(cls)
									(Say 754)
									(event claimed: 1)
								)
								(else
									(HandsOff)
									(= local22 (+ (argueScript state:) 1))
									(harpy1 setScript: wrongThingScript)
									(event claimed: 1)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance island of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0004))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 748)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance fishhook of Prop
	(properties
		x 90
		y 160
		view 652
		loop 4
		signal 24576
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (MousedOn self event))
				(not (== (event type:) evVERB))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 749)
					(event claimed: 1)
				)
				(3 ; Do
					(HandsOff)
					(gCurRoom setScript: getHookScript)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance note of Actor
	(properties
		view 836
		loop 7
		signal 2048
		cycleSpeed 3
		detailLevel 3
	)
)

(instance poly1 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly2 of Polygon
	(properties
		type PBarredAccess
	)
)

