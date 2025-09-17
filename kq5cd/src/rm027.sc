;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 27)
(include sci.sh)
(use Main)
(use Interface)
(use CodeCue)
(use Door)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm027 0
)

(local
	[local0 16] = [0 155 52 158 93 154 200 164 238 159 319 157 319 189 0 189]
	[local16 40] = [319 0 319 136 300 138 279 148 250 148 248 101 239 101 239 148 183 149 172 143 140 140 114 126 160 126 160 122 110 122 89 116 91 101 125 60 112 22 133 0]
	[local56 18] = [117 0 117 16 104 29 96 92 85 106 65 109 81 128 0 129 0 0]
	[local74 8] = [224 165 242 172 211 175 207 171]
	[local82 9] = [1003 40 66 4 11 25 23 31 31]
	[local91 9] = [1039 16 65 2 9 28 29 25 34]
	[local100 9] = [1000 153 50 4 11 24 19 23 30]
	[local109 8] = [1 970 0 971 1 972 0 0]
)

(procedure (localproc_0 param1 param2) ; UNUSED
	(if (< argc 2)
		(= param2 5)
	)
	(OnControl
		CONTROL
		(- (param1 x:) param2)
		(- (param1 y:) param2)
		(+ (param1 x:) param2)
		(+ (param1 y:) param2)
	)
)

(procedure (localproc_1 &tmp temp0)
	(signCel init:)
	(RedrawCast)
	(while (not (OneOf ((= temp0 (Event new:)) type:) evMOUSEBUTTON evKEYBOARD))
		(temp0 dispose:)
	)
	(temp0 dispose:)
	(signCel dispose:)
)

(instance rm027 of KQ5Room
	(properties
		picture 27
		horizon 45
		north 10
		east 6
		west 11
	)

	(method (init)
		(super init:)
		(= global320 168)
		(= global321 100)
		(gEgo normal: 1 setStep: 2 2 view: 2 init:)
		(self
			setFeatures: FakeDoor signPost path27 hayStack house barn
			setRegions: 202 ; owl
		)
		(= global325 3055)
		(gGlobalAudio number: 7777 loop: -1 doNotStop: 1 play:)
		(FDoor init:)
		(SDoor init:)
		(switch gPrevRoomNum
			(east
				(gEgo posn: 314 162)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
			)
			(north
				(gEgo view: 4 posn: 111 47)
				(HandsOff)
				(self setScript: walkDown)
			)
			(west
				(gEgo posn: 5 150)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
			)
			(28
				(gEgo illegalBits: 0 posn: 126 124)
				(SDoor cel: 3)
				(HandsOff)
				(self setScript: fromKitchen)
			)
			(85
				(HandsOff)
				(self setScript: outDoor)
			)
			(else
				(gEgo posn: 245 155)
			)
		)
		(Load rsVIEW 71)
		(gGlobalSound2 number: 24 loop: -1 play:)
		(river1 setCycle: Fwd init:)
		(river2 setCycle: Fwd init:)
		(smoke1 setCycle: Fwd init:)
		(smoke2 setCycle: Fwd init:)
		(poly1 points: @local0 size: 8)
		(poly2 points: @local16 size: 20)
		(poly3 points: @local56 size: 9)
		(poly4 points: @local74 size: 4)
		(self addObstacle: poly2 poly3 poly4)
	)

	(method (doit &tmp temp0 temp1)
		(cond
			(script
				(script doit:)
			)
			(
				(and
					(gEgo edgeHit:)
					(= temp0 (self edgeToRoom: (gEgo edgeHit:)))
				)
				((ScriptID 202 2) register: (gEgo edgeHit:)) ; stdWalkOut
				(self setScript: (ScriptID 202 2)) ; stdWalkOut
			)
			((& (= temp1 (gEgo onControl: 0)) $0002) 0)
			((& temp1 $0004)
				(HandsOff)
				(self setScript: drownHim)
			)
			((& temp1 $4000)
				(HandsOff)
				(self setScript: enterInn)
			)
			((and (& temp1 $0040) (IsFlag 79))
				(HandsOff)
				(self setScript: enterKitchen)
			)
			((& temp1 $0010)
				(HandsOff)
				(self setScript: walkUp)
			)
		)
	)

	(method (handleEvent event)
		(if
			(or
				script
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0004))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 392)
					(event claimed: 1)
				)
				(3 ; Do
					(gEgo setMotion: PolyPath (gEgo x:) 189)
					(event claimed: 1)
				)
			)
		)
	)

	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
		(if (!= newRoomNumber 6)
			(gGlobalSound fade:)
		)
		(if (== newRoomNumber 85)
			(gGlobalSound2 fade:)
		)
	)
)

(instance fromKitchen of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(SDoor z: 1000)
				(gEgo setMotion: MoveTo 104 124 self)
			)
			(1
				(SDoor cel: 3 z: 0 setCycle: Beg self)
			)
			(2
				(= seconds 2)
			)
			(3
				(gGlobalAudio number: 7777 loop: -1 doNotStop: 1 play:)
				(gEgo illegalBits: $8000)
				(SDoor stopUpd:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance walkDown of Script
	(properties)

	(method (doit)
		(super doit:)
		(cond
			((& (gEgo onControl: 1) $0400)
				(gEgo view: 4)
			)
			((& (gEgo onControl: 1) $0080)
				(gEgo view: 2)
			)
		)
	)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: PolyPath 89 113 self)
				(global322 view: 140 loop: 2 cel: 0 cycleSpeed: 3 setCycle: End)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance walkUp of Script
	(properties)

	(method (doit)
		(super doit:)
		(if (& (gEgo onControl: 1) $0400)
			(gEgo view: 4)
			((gEgo head:) view: 4)
		)
	)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: PolyPath 111 47 self)
			)
			(1
				(HandsOn)
				((ScriptID 202 2) register: 1) ; stdWalkOut
				(self setScript: (ScriptID 202 2)) ; stdWalkOut
			)
			(2
				(gCurRoom newRoom: 10)
			)
		)
	)
)

(instance enterInn of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: PolyPath 245 148 self)
			)
			(1
				(gGlobalAudio stop: doNotStop: 0)
				(proc762_1 @local100 3056 self)
			)
			(2
				(FDoor setCycle: End self)
			)
			(3
				(gEgo illegalBits: 0 setMotion: MoveTo 245 139 self)
			)
			(4
				(FDoor setCycle: Beg self)
			)
			(5
				(gEgo illegalBits: $8000)
				(gCurRoom newRoom: 85)
			)
		)
	)
)

(instance enterKitchen of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gEgo setMotion: PolyPath 112 124 self)
			)
			(1
				(proc0_7 gEgo SDoor 5)
				(= cycles 4)
			)
			(2
				(cond
					(register
						(Say 402)
						(HandsOn)
						(self register: 0)
						(client setScript: 0)
					)
					((not (IsFlag 79))
						(Say 398)
						(HandsOn)
						(client setScript: 0)
					)
					(else
						(proc0_7 gEgo SDoor 5)
						(gGlobalAudio number: 8123 loop: 1 play:)
						(SDoor setCycle: End self)
					)
				)
			)
			(3
				(SDoor dispose:)
				(gEgo illegalBits: 0 setMotion: MoveTo 139 124 self)
			)
			(4
				(gEgo illegalBits: $8000)
				(gCurRoom newRoom: 28)
			)
		)
	)
)

(instance searchHay of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: PolyPath 52 131 self)
			)
			(1
				((gEgo head:) moveHead: 0)
				(= cycles 1)
			)
			(2
				((gEgo head:) hide: setCel: (- ((gEgo head:) lastCel:) 1))
				(gEgo view: 293 normal: 0 loop: 0 setCycle: End self)
			)
			(3
				(gEgo loop: 1 cycleSpeed: 4 setCycle: Fwd)
				(= cycles 20)
			)
			(4
				(gEgo loop: 0 cel: 3 setCycle: Beg self)
			)
			(5
				(if
					(and
						(or
							(== ((gInventory at: 16) owner:) 12) ; Stick
							(== ((gInventory at: 8) owner:) 12) ; Shoe
							(== ((gInventory at: 19) owner:) 12) ; Leg_of_Lamb
						)
						(== ((gInventory at: 3) owner:) 27) ; Golden_Needle
					)
					(gGlobalAudio stop:)
					(Say 400)
					(gEgo
						normal: 1
						view: 2
						loop: 7
						cel: 3
						cycleSpeed: 0
						setCycle: KQ5SyncWalk
						setHeading: 0
					)
					((gEgo head:) show:)
					(gEgo setPri: 10)
					(ants1 cycleSpeed: 3 setCycle: End init:)
					(= seconds 8)
				else
					(Say 401 0 1)
					(HandsOn)
					(gEgo
						normal: 1
						view: 2
						loop: 7
						cel: 3
						cycleSpeed: 0
						setCycle: KQ5SyncWalk
					)
					((gEgo head:) show: moveHead: 1)
					(client setScript: 0)
				)
			)
			(6
				(self setScript: singScript)
				(ants1 loop: 6 setCycle: Fwd)
				(ants2 cycleSpeed: 3 setCycle: End self init:)
			)
			(7
				(cls)
				(ants2 loop: 8 setCycle: Fwd)
				(= seconds 7)
			)
			(8)
			(9
				(global322 setPri: 10)
				(= cycles 1)
			)
			(10
				(proc762_0 @local82 @local91 @local109 self)
			)
			(11
				(gGlobalAudio number: 7777 loop: -1 doNotStop: 1 play:)
				(gGlobalSound3 fade:)
				(= cycles 1)
				(cls)
			)
			(12
				(ants2 loop: 7 cel: (- (NumCels ants2) 1) setCycle: Beg self)
			)
			(13
				(ants2 dispose:)
				(ants1 loop: 5 cel: (- (NumCels ants1) 1) setCycle: Beg self)
			)
			(14
				(cls)
				(ants1 dispose:)
				(gEgo setPri: -1 get: 3) ; Golden_Needle
				(SetScore 2)
				(HandsOn)
				(gEgo
					normal: 1
					view: 2
					loop: 7
					cel: 3
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((gEgo head:) show: moveHead: 1)
				(signCel view: 292 loop: 9 cel: 0 posn: 160 100 setPri: 15)
				(client setScript: 0)
			)
		)
	)
)

(instance singScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say 9998 self)
			)
			(1
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance drownHim of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc762_1 @local100 3032 self)
			)
			(1
				(DoAudio audWPLAY 8068)
				(= cycles 1)
			)
			(2
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 71
					cel: 0
					setMotion: MoveTo (gEgo x:) (+ (gEgo y:) 5)
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3
				(gGlobalAudio number: 8068 loop: 1 play:)
				(gEgo
					setLoop: 2
					posn: (- (gEgo x:) 30) (+ (gEgo y:) 10)
					setCycle: Fwd
					setStep: 3 1
					setMotion: MoveTo -15 185
					cycleSpeed: 2
				)
				(= cycles 1)
			)
			(4
				(if (!= (DoAudio audPOSITION) -1)
					(-- state)
				else
					(gGlobalAudio number: 7777 loop: -1 doNotStop: 1 play:)
				)
				(= cycles 1)
			)
			(5
				(if (> (gEgo x:) -15)
					(-- state)
				)
				(= cycles 1)
			)
			(6
				(= seconds 3)
			)
			(7
				(cls)
				(= global330 179)
				(EgoDead)
			)
		)
	)
)

(instance moveScript of Script ; UNUSED
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: End self)
			)
			(1
				(= cycles 10)
			)
			(2
				(client setCycle: Beg self)
			)
			(3
				(= state -1)
				(= seconds (Random 3 10))
			)
		)
	)
)

(instance outDoor of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo
					setPri: 9
					illegalBits: 0
					posn: 245 138
					setMotion: MoveTo 245 140
				)
				(FDoor cycleSpeed: 2 setCycle: End self)
			)
			(1
				(gEgo
					setMotion: MoveTo (gEgo x:) (+ (gEgo y:) 15) self
				)
			)
			(2
				(FDoor setCycle: Beg self)
				(gEgo setPri: -1 illegalBits: $8000)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance path27 of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0002))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 391)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance river1 of Prop
	(properties
		x 233
		y 188
		view 471
		priority 5
		signal 16400
		cycleSpeed 5
		detailLevel 3
	)

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
					(Say 392)
					(event claimed: 1)
				)
				(3 ; Do
					(gEgo setMotion: PolyPath (gEgo x:) 189)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance river2 of Prop
	(properties
		x 74
		y 188
		view 471
		loop 1
		priority 5
		signal 16400
		cycleSpeed 5
		detailLevel 3
	)

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
					(Say 392)
					(event claimed: 1)
				)
				(3 ; Do
					(gEgo setMotion: PolyPath (gEgo x:) 189)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance hayStack of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0008))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 393)
					(event claimed: 1)
				)
				(3 ; Do
					(HandsOff)
					(gCurRoom setScript: searchHay)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance barn of RFeature
	(properties
		nsTop 31
		nsBottom 119
		nsRight 81
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 394)
					(event claimed: 1)
				)
				(3 ; Do
					(Say 399)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance house of RFeature
	(properties
		nsTop 11
		nsLeft 129
		nsBottom 136
		nsRight 311
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 395)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance FDoor of Door
	(properties
		x 253
		y 143
		view 292
		signal 16385
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 396)
					(event claimed: 1)
				)
				(3 ; Do
					(HandsOff)
					(gCurRoom setScript: enterInn)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance SDoor of Door
	(properties
		x 124
		y 127
		view 292
		loop 1
		signal 16385
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 397)
					(event claimed: 1)
				)
				(3 ; Do
					(HandsOff)
					(gCurRoom setScript: enterKitchen)
					(event claimed: 1)
				)
				(4 ; Inventory
					(switch (gInventory indexOf: (gTheIconBar curInvIcon:))
						(1
							(HandsOff)
							(enterKitchen register: 1)
							(gCurRoom setScript: enterKitchen)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance FakeDoor of RFeature
	(properties
		nsTop 83
		nsLeft 117
		nsBottom 126
		nsRight 142
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 397)
					(event claimed: 1)
				)
				(3 ; Do
					(if (not (IsFlag 79))
						(Say 398)
					else
						(gCurRoom setScript: enterKitchen)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance signPost of RFeature
	(properties
		nsTop 107
		nsLeft 163
		nsBottom 122
		nsRight 192
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(localproc_1)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance smoke1 of Prop
	(properties
		x 187
		y 3
		view 292
		loop 3
		cycleSpeed 3
		detailLevel 3
	)
)

(instance smoke2 of Prop
	(properties
		x 294
		y 7
		view 292
		loop 4
		cycleSpeed 3
		detailLevel 3
	)
)

(instance signCel of Prop
	(properties
		x 160
		y 100
		view 292
		loop 9
		priority 15
		signal 16
	)
)

(instance theMouth of Prop ; UNUSED
	(properties
		x 50
		y 52
		view 325
		loop 3
		cel 7
		signal 16
	)
)

(instance ants1 of Prop
	(properties
		x 29
		y 132
		view 292
		loop 5
		priority 9
		signal 16
	)
)

(instance ants2 of Prop
	(properties
		x 30
		y 135
		view 292
		loop 7
		priority 9
		signal 16
	)
)

(instance kingAnt of Prop ; UNUSED
	(properties
		x 51
		y 80
		view 325
		priority 13
		signal 17
	)
)

(instance blink of Prop ; UNUSED
	(properties
		x 49
		y 43
		view 325
		loop 4
		priority 14
		signal 16
	)
)

(instance wArm of Prop ; UNUSED
	(properties
		x 31
		y 69
		view 325
		loop 5
		cel 6
		priority 14
		signal 16
	)
)

(instance eArm of Prop ; UNUSED
	(properties
		x 76
		y 74
		view 325
		loop 6
		cel 6
		priority 14
		signal 16
	)
)

(instance pin of Prop ; UNUSED
	(properties
		x 53
		y 55
		view 325
		cel 1
		priority 14
		signal 16
	)
)

(instance cloud of Prop ; UNUSED
	(properties
		x 51
		y 52
		view 214
		priority 12
		signal 16
	)
)

(instance wAnt of Prop ; UNUSED
	(properties
		x 31
		y 24
		view 325
		loop 1
		cel 4
		priority 14
		signal 16400
	)
)

(instance eAnt of Prop ; UNUSED
	(properties
		x 64
		y 26
		view 325
		loop 2
		cel 4
		priority 14
		signal 16400
	)
)

(instance poly1 of Polygon
	(properties
		type PTotalAccess
	)
)

(instance poly2 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly3 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly4 of Polygon
	(properties
		type PBarredAccess
	)
)

