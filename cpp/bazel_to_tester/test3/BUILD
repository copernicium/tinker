cc_test(
	name="posedge_trigger_test",
	srcs=["util/posedge_trigger.cpp","util/posedge_trigger.h"],
	copts=["-DPOSEDGE_TRIGGER_TEST"],
	deps=[],
	timeout="short"
)

cc_library(
	name="point",
	srcs=["util/point.cpp"],
	hdrs=["util/point.h"],
	deps=[],
)

cc_test(
	name="point_test",
	srcs=[],
	deps=[":point"],
	copts=["-DPOINT_TEST"],
	timeout="short"
)